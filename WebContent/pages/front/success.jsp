<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>主界面</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<frameset rows="20%,*" border="5px" bordercolor="black" frameborder="yes">
  	<frame src="pages/front/top.jsp" name="top" noresize scrolling="no"/>
  	<frameset cols="15%,*">
  	<!-- 这里name等于left，和下面的name=right分别是什么意思 -->
  		<frame src="pages/front/left.jsp" name="left" noresize/>
  		<frame src="FindAlbumByUserServlet?op=showAlbum&currentPage=1" name="right"/>
  	</frameset>
  </frameset>
<body>
</body>
</html>