<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
	ul{
		list-style-type:none;
		background-color:#1D9BE7;
		border:2px solid purple;
		width:120px;
		height:300px;
	}
	ul li{
		padding:15px;
	}
	a{
		text-decoration: none;
		color:black;
	}
	a:HOVER {
		text-decoration:underline;
		color:red;
	}
  </style>
  <body bgcolor="gray">
  	<!-- 获取当前登陆用户的权限 -->
  	<input type="hidden" id="prov" value="${uflag}"/>
   	<ul>
   		<li><a href="FindAlbumByUserServlet?op=showAlbum&currentPage=1" target="right">查看相册</a></li>
   		<li><a href="pages/front/addAlbum.jsp" target="right">新建相册</a></li>
   		<li><a href="FindAlbumByUserServlet" target="right">上传照片</a></li>
   		<li><a href="FindAlbumByUserServlet?op=query" target="right">照片分享</a></li>
   		<li><a href="pages/front/updatePassword.jsp" target="right">密码修改</a></li>
   		<li><a href="QueryPhotoServlet?currentPage=1&name=Temp" target="right">圈圈</a></li>
   		<!-- 标识当前需要被隐藏的选项 -->
   		<li id="hid"><a href="ShowUserServlet?currentPage=1" target="right">用户查看</a></li>
   	</ul>
  </body>
  <script>
  	window.onload = function hid(){
		var prov = document.getElementById("prov").value;
		//判断当前登陆用户是否为管理员
		if(prov != 1){
			//不等于1的话就不是管理员，不是管理员的话就需要隐藏标签
			document.getElementById("hid").style.display="none";
		}
  	  }
  </script>
</html>
