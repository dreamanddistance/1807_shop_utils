<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="common/shop_util.js"></script>
<script type="text/javascript">
$(function(){
	
	// 先获取商品的大类和小类id
	var parentId ="${goodsInfo.goods_parentid}";
	var fatherId ="${goodsInfo.goods_fatherid}";
	
	// 1.初始化大类
	initGoodsType("goods_parentid",parentId,'0');
	
	// 2.初始化小类
	initGoodsType("goods_fatherid",fatherId,parentId);
	
	// 3.给大类绑定改变事件
	$("#goods_parentid").change(function(){
		
		// 清空之前的数据
		$("#goods_fatherid").find("option").remove();
		
		// 3.获取用户选中的大类的id
		var parentId = $(this).val();
		
		// 4.初始化小类(根据大类查询小类)
		initGoodsType("goods_fatherid",'',parentId);
	});
	
	
	// 4.给上传文件组件动态绑定改变事件
	$("#goods_pic").change(function(){
		
		// 1.获取用户新上传的图片对象
		var file = this.files[0];
		
		// 2.把文件对象转成一个临时文件，返回的是临时文件的路径
		var fileUrl = window.URL.createObjectURL(this.files[0]);
		
		// 3.替换掉老图片
		$("#show_pic").attr("src",fileUrl);
	});
	
	
})
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>修改商品信息</span></div>
    <form method="post" action="GoodsInfoServlet?action=udpateGoodsInfo" enctype="multipart/form-data">
    	<input type="hidden" name="id" value="${goodsInfo.id}"/>
    	<input type="hidden" name="goods_pic" value="${goodsInfo.goods_pic}"/>
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" value="${goodsInfo.goods_name}"  type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="goods_parentid" id ="goods_parentid">
	    			<option value="0">无</option>
	    		</select>
	    		
	    </i></li>
	    <li><label>所属小类</label>
	    	<select name="goods_fatherid" id ="goods_fatherid">
	    		<option value="0">无</option>
	    		<option value=""></option>
	    	</select>
	    </i></li>
	    <li><label>商品价格</label><input name="goods_price" type="text" class="dfinput" value="${goodsInfo.goods_price}"/><i>标题不能超过30个字符</i></li>
	    <li><label></label>
	    	<img src="images/${goodsInfo.goods_pic }" id="show_pic" style="width: 300px;" alt="" />
	    </li>
	    <li><label>商品图片</label><input name="goods_pic" id="goods_pic" type="file"/></li>
	    <li><label>商品描述</label>
	    	<textarea rows="8" cols="40" name="goods_description"  >${goodsInfo.goods_description}</textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

