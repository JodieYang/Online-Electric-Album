<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
  		//获得错误信息显示的位置
  		//前面的先不管，这里error是HTML标签的id值，可以从下面看出，里面一开始什么都没有
  		var e = document.getElementById("error");
  		if(path.length<1){
  			e.innerText="请选择图片路径";
  			return false;
  		}else{
  			e.innerText="";
  		}
  		form1.action="PhotoUploadServlet";
  	}
  </script>
  <body bgcolor="gray">
  <%
  	String sid = request.getAttribute("id").toString();
    int id=Integer.parseInt(sid);
    String name=request.getAttribute("name").toString();
   %>
   <!-- 涉及到上传文件，用multipart/form-data，将表单数据编码成二进制类型 -->
    <form name="form1" onsubmit="return checkFile()" method="post" enctype="multipart/form-data">
    	<input type="hidden" value="<%=id%>" name="id"/>
    	<input type="hidden" value="<%=name%>" name="name">
    	<table border="1">
    		<tr bgcolor="#1E90FF">
    			<th colspan="2" style="font-size:25;font-style: oblique">分享照片</th>
    		</tr>
    		<tr>
    			<th colspan="2">
    			<!-- img id为photo，定义了图片显示的宽度和高度 -->
    				<img id="p" width="150px" height="150px"/>
    			</th>
    		</tr>
    		<tr>
    			<th colspan="2" id="error" style="color:red"></th>
    		</tr>
    		<tr>
    			<th colspan="2">
    			<!-- 这里是文件名，这个标签id置为s -->
    				<input type="file" name="file" id="s" style="width:300px" onchange="showImg()"/>
    			</th>
    		</tr>
    		<tr>
    			<td>添加心情</td>
    			<td>
    				<textarea rows="5" name="pdescription" cols="30"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="submit" value="分享" class="btn"/>
    				<input type="button" value="取消" class="btn"  onclick="history.back()"/>
    			</th>
    		</tr>
    	</table>
    </form>
  </body>
  <script>
  	function showImg(){
  		//获取文件控件中取到的值
  		//这里path是图片的路径（文件名）（先理解为绝对路径），通过s获得的
  		var path = document.getElementById("s").value;
  		//获得图片显示的区域
  		var img = document.getElementById("p");
  		//为图片添加src属性值，也就是路径
  		img.src=path;
  	}
  </script>
</html>
