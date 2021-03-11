<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--引入标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAlbum.jsp' starting page</title>
    
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
  		text-decoration: none;
  	}
  	a:HOVER {
		color:green;
		text-decoration: underline;
		background-color: red;
	}
	a:VISITED {
		color: black;
	}
  </style>
  <body bgcolor="gray">
    <form>
    	<table border="0">
	    	<tr>
		    	<th>
		    		${guserid}:
		    	</th>
	    	</tr>
    	<!-- 循环迭代，num是元素下标 -->
    		<c:forEach items="${list}" var="album" varStatus="num">
							<tr>
								<th colspan="2">
								<!-- 是在这里把aid传进去的 -->
									<a href="ShowGPhotoServlet?id=${album.id}&currentPage=1&guserid=${guserid}"><img src="img/test.jpg" border="0" width="150px" height="170px" align="middle"/></a>
								</th>
							</tr>
							<tr>
								<td bgcolor="pink">${album.name}</td>
								<td align="right">
								<!-- 是在这里把aid传进去的 ，也是一样的，已经把aid改成了id-->
									<a href="javascript:del('${album.id}','${guserid} ')">删除</a>
								</td>
							</tr>		
    		</c:forEach>
    	</table>
    </form>
  </body>
  <script>
  	function del(v,v1){
		if(window.confirm("确认删除")){
			window.location.href="DelAlbumServlet1?id="+v+"&userid="+v1;	
		}
  	}
  </script>
</html>
