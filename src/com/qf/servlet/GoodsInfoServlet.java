package com.qf.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.qf.entity.GoodsInfo;
import com.qf.entity.Page;
import com.qf.service.IGoodsInfoService;
import com.qf.service.impl.GoodsInfoServiceImpl;

public class GoodsInfoServlet extends BaseServlet {

	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();

	public String getGoodsInfoPage(Page<GoodsInfo> page, HttpServletRequest request) {
		goodsInfoService.getPage(page);
		page.setUrl("GoodsInfoServlet?action=getGoodsInfoPage");
		request.setAttribute("page", page);
		return "forward:back/goods/goodsList.jsp";
	}

	public String addGoodsInfo(HttpServletRequest request) {

		GoodsInfo goodsInfo = uploadFile(request);

		goodsInfoService.add(goodsInfo);
		return "redirect:GoodsInfoServlet?action=getGoodsInfoPage";
	}

	private GoodsInfo uploadFile(HttpServletRequest request) {
		// 确定文件上传的目录
		String path = request.getServletContext().getRealPath("images");

		// 1.创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 2.上传文件核心的组件
		ServletFileUpload upload = new ServletFileUpload(factory);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 3.解析req
			List<FileItem> fileItemList = upload.parseRequest(request);
			for (FileItem fileItem : fileItemList) {
				String fieldName = fileItem.getFieldName(); // 表单中的name属性
				if (fileItem.isFormField()) { // 判断是否为文本类型
					String value = fileItem.getString("utf-8"); // 属性的文本值
					map.put(fieldName, value);
				} else {

					String fileName = fileItem.getName();
					if (fieldName != null && !"".equals(fileName)) { // 有文件名称就代表用户选择文件
						// 把文件名称保存到map中
						map.put(fieldName, fileName);

						// 上传文件
						FileOutputStream ops = null;
						try {
							// 指定文件输出的路径
							ops = new FileOutputStream(path + File.separator + fileName);

							// IO流的拷贝
							IOUtils.copy(fileItem.getInputStream(), ops);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (ops != null) {
								try {
									ops.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

					}
				}
			}

			GoodsInfo goodsInfo = new GoodsInfo();
			// 把map中的数据拷贝到对象中
			BeanUtils.populate(goodsInfo, map);
			return goodsInfo;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getGoodsInfoById(Integer id, HttpServletRequest request) {
		GoodsInfo goodsInfo = goodsInfoService.getById(id);
		request.setAttribute("goodsInfo", goodsInfo);
		return "forward:back/goods/goodupdate.jsp";
	}

	public String udpateGoodsInfo(HttpServletRequest request) {
		GoodsInfo goodsInfo = uploadFile(request);
		goodsInfoService.update(goodsInfo);
		return "redirect:GoodsInfoServlet?action=getGoodsInfoPage";
	}

}
