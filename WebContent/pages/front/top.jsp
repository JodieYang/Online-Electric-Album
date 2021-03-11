<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
    <title>My JSP 'top.jsp' starting page</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<style>
  	h1{
  		font-size:45px;
  		font-family:华文行楷;
  		color:
  	}
  	#time{
  		position:absolute;
  		height:200px;
  		top:100px;
  		left:75%;
  	}
  	#time1{
  	position:absolute;
  		height:200px;
  		top:100px;
  		left:-5%;
  	}
</style>
<body bgcolor="gray" onload="getNowTime()">
    <center><h1>电子相册管理系统(AlbumSystem)</h1></center>
    <span id="time1" style="text-indent:3cm">欢迎你,${username}</span>
    <span id="time">当前时间:<span id="nowtime"></span>&nbsp;&nbsp;<a href="javascript:window.location.replace('pages/front/login.jsp')" target="_top">退出系统</a></span>
  </body>
  <script>
		function getNowTime(){
			//获得显示时间的位置
			var showtime = document.getElementById("nowtime");
			//获取当前系统时间
			var date = new Date();
			//获取年月日时分秒
			var year = date.getYear();
			var month = date.getMonth()+1;
			if(month.toString().length  < 2){
				month = "0"+month;
			}
			var day = date.getDate();
			if(day.toString().length  < 2){
				day = "0"+day;
			}
			var hour = date.getHours();
			if(hour.toString().length  < 2){
				hour = "0"+hour;
			}
			var minu = date.getMinutes();
			if(minu.toString().length < 2){
				minu = "0"+minu;
			}
			var second = date.getSeconds();
			if(second.toString().length <2){
				second = "0"+second;
			}
			var nowtime = year+"/"+month+"/"+day+" "+hour+":"+minu+":"+second;
			showtime.innerHTML="<font color='lightblue'>"+nowtime+"</font>";
			window.setTimeout("getNowTime()",1000);
		}
  </script>
</html>