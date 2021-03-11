<%@ page language="java" import="java.util.*,mat.java.beans.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPhoto.jsp' starting page</title>
    
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
		top:5%;
		left:30%;
		width:500px;
		height:400px;
		border:2px solid purple;
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
  	function checkFile(){
  		//获取表单对象
  		var form1 = document.form1;
  		//获得文件控件中的信息(路径)
  		var path = form1.file.value;
  		//获得所选相册
  		var id = form1.id.value;
  		//获得错误信息显示的位置
  		var e = document.getElementById("error");
  		if(path.length<1){
  			e.innerText="请选择图片路径";
  			return false;
  		}else{
  			e.innerText="";
  		}
  		if(id==0){
  			e.innerText="请选择相册";
  			return false;
  	  	}else{
  	  		e.innerText="";
  	  	}
  		form1.action="PhotoUploadServlet";
  	}
  </script>
  <body bgcolor="gray">  
    <form name="form1" onsubmit="return checkFile()" method="post" enctype="multipart/form-data">
    	<table border="1">
    		<tr bgcolor="#1E90FF">
    			<th colspan="2" style="font-size:25;font-style: oblique">上传照片</th>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<img id="p" width="150" height="150"/>
    			</th>
    		</tr>
    		<tr>
    			<th colspan="2" id="error" style="color:red"></th>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="file" name="file" id="s" style="width:300px" onchange="showImg()"/>
    			</th>
    		</tr>
    		<tr>
	    		<td>选择相册</td>
    			<td><select name="id">
    					<option value="0">==请选择==</option>
    					<c:forEach items="${list}" var="a">
    					<option value="${a.id}">${a.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td>照片描述</td>
    			<td>
    				<textarea rows="5" name="pdescription" cols="30"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="submit" value="上传" class="btn"/>
    				<input type="button" value="取消" class="btn" onclick="history.back()"/>
    			</th>
    		</tr>
    	</table>
    </form>
  </body>
  <script>
  	function showImg(){
  		//获取文件控件中取到的值
  		var path = document.getElementById("s").value;
  		//获得图片显示的区域
  		var img = document.getElementById("p");
  		//为图片添加src属性值
  		img.src=path;
  	}
  </script>
</html>
