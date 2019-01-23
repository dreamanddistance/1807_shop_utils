package com.qf.servlet;

import java.awt.image.RescaleOp;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.domain.GoodsInfoDomain;
import com.qf.entity.Address;
import com.qf.entity.GoodsInfo;
import com.qf.entity.GoodsType;
import com.qf.entity.Order;
import com.qf.entity.OrderDetail;
import com.qf.entity.ShopCart;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import com.qf.service.IOrderDetail;
import com.qf.service.IOrderService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.service.impl.GoodsInfoServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.service.impl.OrderDetailServiceImpl;
import com.qf.service.impl.OrderServceImpl;

public class ShopServlet extends BaseServlet {
	
	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
	private IAddressService addressService = new AddressServiceImpl();
	private IOrderService orderService = new OrderServceImpl();
	private IOrderDetail orderDetailService = new OrderDetailServiceImpl();
	
	
	public String index(HttpServletRequest request,HttpServletResponse response){
		
		// 1.查询所有的商品信息
		List<GoodsInfo> goodsInfoList = goodsInfoService.getList();
		
		// 2.查询所有商品类别
		List<GoodsType> goodsTypeList = goodsTypeService.getList();
		
		// 3.把数据放入req作用域中
		request.setAttribute("gtList", goodsTypeList);
		request.setAttribute("giList", goodsInfoList);

		// 4.转发
		return "forward:index.jsp";
	}
	
	public String getGoodsInfoById(Integer id,HttpServletRequest request){
		GoodsInfo goodsInfo = goodsInfoService.getById(id);
		request.setAttribute("goodsInfo", goodsInfo);
		return "forward:introduction.jsp";
	}
	
	public void gotoShopCart(Integer id,Integer count,HttpServletRequest req){
		
		// 1.根据商品id查询对象
		GoodsInfo goodsInfo = goodsInfoService.getById(id);
		
		
		// 2.获取购物车对象
		ShopCart shopCart = ShopCart.getShopCartIns(req.getSession());
		
		// 3.把商品添加到购物车中
		GoodsInfoDomain goodsInfoDomain = new GoodsInfoDomain();
		goodsInfoDomain.setCount(count);
		try {
			BeanUtils.copyProperties(goodsInfoDomain, goodsInfo);
			shopCart.add(goodsInfoDomain);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateGoodsInfoDomainCount(Integer id,Integer count,HttpServletRequest request){
		
		// 1.获取购物车对象
		ShopCart shopCart = ShopCart.getShopCartIns(request.getSession());
		
		shopCart.updateCount(id,count);
	}
	
	
	public String deleteGoodsInfoDomain(Integer id,HttpServletRequest request){
		
		// 1.获取购物车对象
		ShopCart shopCart = ShopCart.getShopCartIns(request.getSession());
		
		shopCart.delete(id);
		return "redirect:shopcat.jsp";
	}
	
	public String gotoPay(HttpServletRequest request,HttpServletResponse response){
		
		// 1.判断用户是否登录
		User user = (User)request.getSession().getAttribute("user");
		
		if(user == null){
//			return "forward:login.jsp";
			user = new User();
			user.setId(20);
		}
		
		// 2.根据用户id查询用户地址
		List<Address> addresseList = addressService.getAddressListByUserId(user.getId());
		
		// 3.把地址集合放入到req作用域中
		request.setAttribute("addressList", addresseList);
		
		// 4.跳转到支付页面
		return "forward:pay.jsp";
	}
	
	public String pay(HttpServletRequest request){
		
		
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
//			return "forward:login.jsp";
			user = new User();
			user.setId(20);
		}
		
		// 1.先获取表单中的数据
		String shouhuoren = request.getParameter("shouhuoren");
		String phone= request.getParameter("phone");
		String address = request.getParameter("address");
		String express= request.getParameter("express");
		String bank= request.getParameter("bank");
		
		ShopCart shopCart = ShopCart.getShopCartIns(request.getSession());
		
		// 2.先插入订单
		Order order = new Order();
//		order.setO_orderdate(o_orderdate);
		order.setO_paycount(shopCart.getPrice()); // 总金额
		order.setO_paytype(bank);
		order.setO_sendtype(express);
		order.setO_shaddress(address);
		order.setO_shperson(shouhuoren);
		order.setO_shphone(phone);
		order.setUserid(user.getId());
		
		int orderId = orderService.add(order); // 主键回填
		
		List<GoodsInfoDomain> list = shopCart.getList();
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		
		for (GoodsInfoDomain goodsInfoDomain : list) {
			
			OrderDetail orderDetail = new OrderDetail();
//			orderDetail.setGoods_date(goods_date);
			orderDetail.setGoods_description(goodsInfoDomain.getGoods_description());
			orderDetail.setGoods_total_price(goodsInfoDomain.getCount()*goodsInfoDomain.getGoods_price_off());
			orderDetail.setGoodsid(goodsInfoDomain.getId()); // 商品的id
			orderDetail.setO_orderid(orderId); // 订单的id
			orderDetail.setGoodsname(goodsInfoDomain.getGoods_name()); 
			orderDetail.setGoodsnum(goodsInfoDomain.getCount());
			orderDetail.setGoodspic(goodsInfoDomain.getGoods_pic());
			orderDetail.setGoodsprice(goodsInfoDomain.getGoods_price());
			
			// 3.插入订单详情
			orderDetailService.add(orderDetail);
			// 先把东西放入到集合中
			orderDetails.add(orderDetail);
		}
//		orderDetailService.add(orderDetails);
		return null;
	}
	
}
