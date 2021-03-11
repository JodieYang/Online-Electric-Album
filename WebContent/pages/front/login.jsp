<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  	table{
  		position:absolute;
  		top:5cm;
  		left:10cm;
  		width:400px;
  		height:250px;
  		border:2px solid #2BA8E8;
  	}
  	.input{
  		width:260px;
  		height:30px;
  		font-size:20px;
  	}
  	.btn{
  		cursor:pointer;
  		height:40px;
  		width:70px;
  		font-size:20px;
  		font-weight: bold;
  	}
  	.title{
  		font-style: italic;
  	}
  </style>
  <body bgcolor="gray" text="white">
  		<!-- 表单开始 -->
   		<form name="form1" method="get"> 			
   			<table border="1">
   				<tr>
   					<th colspan="2" style="font-size:30px;font-family: 华文行楷">电子相册系统</th>
   				</tr>
   				<tr>
   					<th colspan="2" style="color:red" id="msg">${msg}</th>
   				</tr>
   				<tr>
   					<td class="title">用户名:</td>
   					<td><input type="text" class="input" name="username"></td>
   				</tr>
   				<tr>	
   					<td class="title">密码:</td>
   					<td><input type="password" class="input" name="password"></td>
   				</tr>
   				<tr>
   					<td colspan="2" align="center">
   						<input type="button" value="登陆" class="btn" onclick="return login()"/>
   						<input type="button" value="注册" class="btn" onclick="return register()"/>
   					</td>
   				</tr>
   			</table>
   		</form>
   		<!-- 表单结束 -->
  </body>
  <script>
	//获取表单对象
	var form1 = document.form1;
	//获取错误信息显示位置的对象
	var msg = document.getElementById("msg");
  	
  	function login(){
		//获取用户名
		var username = form1.username.value;
		//获取密码
		var password = form1.password.value;
  		
  		if(username.length<1){
  			msg.innerText="请输入用户名";
  			return false;
  		}else{
  			msg.innerText="";
  		}
  		if(password.length<1){
  			msg.innerText="请输入密码";
  			return false;
  		}else{
  			msg.innerText="";
  		}
  		//页面跳转
  		form1.action="MainServletTest";
  		form1.submit();
  	}
  	function register(){
		msg.innerText="";
		form1.action="pages/front/register.jsp";
		form1.submit();
  	}
  </script>
</html>
