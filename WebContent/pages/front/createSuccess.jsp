<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'createSuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style>
  	a{
  		font-style: italic;
  		font-size: 20
  	}
  </style>
  <body bgcolor="gray">
    <img src="img/test.jpg" width="150" height="200"/>
    <div>相册${album.name}创建成功 
    	 <a href="pages/front/addAlbum.jsp">继续创建</a> 
    	 <a href="pages/front/addPhoto.jsp?id=${album.id}">上传照片</a>
   </div>
  </body>
</html>
