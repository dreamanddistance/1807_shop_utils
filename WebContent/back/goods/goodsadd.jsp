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
	
	// 1.初始化大类
	initGoodsType("goods_parentid",'','0');
	
	// 2.给大类绑定改变事件
	$("#goods_parentid").change(function(){
		
		// 清空之前的数据
		$("#goods_fatherid").find("option").remove();
		
		// 3.获取用户选中的大类的id
		var parentId = $(this).val();
		debugger
		
		// 4.初始化小类(根据大类查询小类)
		initGoodsType("goods_fatherid",'',parentId);
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
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form method="post" enctype="multipart/form-data" action="GoodsInfoServlet?action=addGoodsInfo">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="goods_parentid" id="goods_parentid">
	    			<option value="">无</option>
	    		</select>
	    		
	    </li>
	    <li><label>所属小类</label>
	    		<select name="goods_fatherid" id="goods_fatherid">
	    			<option value="0">无</option>
	    		</select>
	    </li>
	     <li><label>商品图片</label><input name="goods_pic" type="file" /></li>
	    <li><label>商品价格</label><input name="goods_price" type="text" class="dfinput" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="goods_description" ></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

