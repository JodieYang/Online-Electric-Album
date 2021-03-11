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
    		<c:choose>
    			<c:when test="${empty list}">
    			<tr>
    			<td>暂无动态</td>
    			</tr>
    			</c:when>
    			<c:otherwise>
	    		<c:forEach items="${list}" var="photo" varStatus="num">
	    			<c:if test="${num.index%4==0}">
	    				<tr>
	    			</c:if>
	  	    			<td>
							<table>
								<tr>
									<th>
									${photo.userid}: 
									</th>
									<td>
									${photo.pdescription}
									</td>
								</tr>
								<tr>
									<th>
										<a href="CommentServlet?userid=${photo.userid}&pid=${photo.pid}&id=${photo.albumid}"><img src="/photos/${photo.userid}/${photo.albumid}/${photo.pname}" border="0" width="130px" height="130px"/></a>
									</th>
								</tr>
								<tr>
									<td bgcolor="pink" width=80px>${photo.pname}</td>
								</tr>
								<tr><td>上传时间:${photo.puploadtime}</td></tr>
							</table>  			
		    			</td>
	    			<c:if test="${num.index%4==3 || num.last}">
	    			</c:if>
	    		</c:forEach>
	    		</c:otherwise>
	    		</c:choose>
    			<tr>
    				<td colspan="4" align="right">
    					<a href="QueryPhotoServlet?name=Temp&currentPage=1">首页</a>
    					<a href="QueryPhotoServlet?name=Temp&currentPage=${currentPage-1}">上一页</a>
    					
    					<a href="QueryPhotoServlet?name=Temp&currentPage=${currentPage+1}">下一页</a>
    					<a href="QueryPhotoServlet?name=Temp&currentPage=${totalPage}">尾页</a>
    				</td>
    			</tr>
    	</table>
    </form>
  </body>
</html>
