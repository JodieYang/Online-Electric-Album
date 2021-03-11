	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addAlbum.jsp' starting page</title>
    
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
  		top:20%;
  		left:30%;
  		heigth:380px;
  		width:450px;
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
  		border:0;
  		background-color: green;
  		color:white;
  	}
  </style>
  <script>
	function checkName(){
		//获取输入的相册名
		var name = document.form1.name.value;
		//获取显示错误提示信息的位置
		var msg = document.getElementById("msg");
		if(name.length<1){
			msg.innerText="请输入相册名";
			return false;
		}else{
			msg.innerText="";
		}
		//验证通过,提交到后台
		return true;
	}  
  </script>
  <body bgcolor="gray">
    <form action="AddAlbumServlet" onsubmit="return checkName()" name="form1">
    	<table border="1">
    		<tr bgcolor="#1E90FF">
    			<th colspan="2" style="font-size:25;font-style: oblique">新建相册</th>
    		</tr>
    		<tr>
    			<th id="msg" colspan="2" style="color:red">${msg}</th>
    		</tr>
    		<tr>
    			<td align="right">相册名:</td>
    			<td>
    				<input type="text" name="name" class="input"/>
    			</td>
    		</tr>
    		<tr>
    			<td align="right" valign="top">相册描述:</td>
    			<td>
    				<textarea rows="5" cols="30" name="description"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="submit" value="新建" class="btn"/>
    				<input type="button" value="返回" class="btn" onclick="history.back()"/>
    			</th>
    		</tr>
    	</table>
    </form>
  </body>
</html>
