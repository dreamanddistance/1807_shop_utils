package com.qf.servlet;

import com.qf.service.impl.GoodsTypeServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;

public class GoodsTypeServlet extends BaseServlet {

	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

	public String getGoodsTypePage(Page<GoodsType> page, HttpServletRequest request) {

		// 1.调用service给page中的赋值
		goodsTypeService.getPage(page);

		// 2.设置url
		page.setUrl("GoodsTypeServlet?action=getGoodsTypePage");

		// 3.放到req作用域中
		request.setAttribute("page", page);

		// 4.转发
		return "forward:back/goodstype/goodstype.jsp";
	}
	
	public void getGoodsTypeListByParentId(Integer parentId,HttpServletResponse response) {
		
		 // 1.根据父类id查询所有的商品类别
		List<GoodsType> goodsTypeList = goodsTypeService.getGoodsTypeListByParentId(parentId);
		
		// 2.把list转成json字符串
		String json = new Gson().toJson(goodsTypeList);
		
		try {
			// 3.把json字符串写到响应体中
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String addGoodType(GoodsType goodsType) {
		goodsTypeService.add(goodsType);
		return "redirect:GoodsTypeServlet?action=getGoodsTypePage";
	}
	
	public String getGoodsTypeById(Integer id,HttpServletRequest request) {
		GoodsType goodsType = goodsTypeService.getById(id);
		request.setAttribute("goodsType", goodsType);
		return "forward:back/goodstype/goodstypeupdate.jsp";
	}

}
