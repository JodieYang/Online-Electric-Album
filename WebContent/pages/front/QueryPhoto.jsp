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
  		//��ȡ������
  		var form1 = document.form1;
  		//����ļ��ؼ��е���Ϣ(·��)
  		var path = form1.file.value;
  		//��ô�����Ϣ��ʾ��λ��
  		//ǰ����Ȳ��ܣ�����error��HTML��ǩ��idֵ�����Դ����濴��������һ��ʼʲô��û��
  		var e = document.getElementById("error");
  		if(path.length<1){
  			e.innerText="��ѡ��ͼƬ·��";
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
   <!-- �漰���ϴ��ļ�����multipart/form-data���������ݱ���ɶ��������� -->
    <form name="form1" onsubmit="return checkFile()" method="post" enctype="multipart/form-data">
    	<input type="hidden" value="<%=id%>" name="id"/>
    	<input type="hidden" value="<%=name%>" name="name">
    	<table border="1">
    		<tr bgcolor="#1E90FF">
    			<th colspan="2" style="font-size:25;font-style: oblique">������Ƭ</th>
    		</tr>
    		<tr>
    			<th colspan="2">
    			<!-- img idΪphoto��������ͼƬ��ʾ�Ŀ�Ⱥ͸߶� -->
    				<img id="p" width="150px" height="150px"/>
    			</th>
    		</tr>
    		<tr>
    			<th colspan="2" id="error" style="color:red"></th>
    		</tr>
    		<tr>
    			<th colspan="2">
    			<!-- �������ļ����������ǩid��Ϊs -->
    				<input type="file" name="file" id="s" style="width:300px" onchange="showImg()"/>
    			</th>
    		</tr>
    		<tr>
    			<td>�������</td>
    			<td>
    				<textarea rows="5" name="pdescription" cols="30"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="submit" value="����" class="btn"/>
    				<input type="button" value="ȡ��" class="btn"  onclick="history.back()"/>
    			</th>
    		</tr>
    	</table>
    </form>
  </body>
  <script>
  	function showImg(){
  		//��ȡ�ļ��ؼ���ȡ����ֵ
  		//����path��ͼƬ��·�����ļ������������Ϊ����·������ͨ��s��õ�
  		var path = document.getElementById("s").value;
  		//���ͼƬ��ʾ������
  		var img = document.getElementById("p");
  		//ΪͼƬ���src����ֵ��Ҳ����·��
  		img.src=path;
  	}
  </script>
</html>
