<%@ page language="java" import="java.util.*,mat.java.beans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowSinglePhoto.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script>
  	//定义一个XMLHttpRequest对象
  	var xmlReq;
  	//判断浏览器
  	function getBrowse(){
  		if(window.ActiveXObject){
  			//IE内核
  			xmlReq =new ActiveXObject("microsoft.XMLHttp");
  		}else{
  			//其他浏览器
  			xmlReq = new XMLHttpRequest();
  		}
  	}
  	function getAname(){
  		getBrowse();
  		//获取当前相册的id
  		var id = document.getElementById("id").value;
  		var url = "FindAlbumServlet?id="+id;
  		//一个访问对象
  		xmlReq.open("post", url, true);
  		//当访问状态发生改变,调用回调函数
  		xmlReq.onreadystatechange = goback;
  		//执行
  		xmlReq.send(null);
  	}
  
  	function goback(){
  		//判断请求状态
  		if(xmlReq.readyState == 4){
  			//获取服务器端响应状态
  			if(xmlReq.status == 200){
  				//服务器端响应过来的信息
  				var name = xmlReq.responseText;
  				//将相册名写入到指定位置
  				document.getElementById("name").innerHTML=name;
  			}
  		}
  	}
  </script>
  <body bgcolor="gray" onload="getAname()">
  <% 
  	List<Photo> photos = (List<Photo>)request.getAttribute("photos");
  	Photo p = (Photo)request.getAttribute("p");
  	String userid=request.getSession().getAttribute("userid").toString();
    String sid=String.valueOf(p.getAlbumid());
    String partpath="/photos/"+userid+"/"+sid+"/";
   %>
  	<center>
   			<input type="hidden" id="id" value="${p.albumid}"/>
   		<table width="500px" height="400px">
   			<tr>
   				<td valign="middle" onmouseover="show('up')" onmouseout="hide('up')" width="50px"><a href=""><img id="up" src="img/up.emf" alt="上一张" border="0" style="display:none"></a></td>
   					<th><img src="<%=partpath %>${p.pname}" border="0" width="300px" height="300px"/></th>
   				<td valign="middle" onmouseover="show('down')" onmouseout="hide('down')" width="50px"><a href=""><img id="down" src="img/down.emf" alt="下一张" border="0" style="display:none"></a></td>
   			</tr>
   			<tr>
   				<td align="right" colspan="3">
   					上传时间:${p.puploadtime}&nbsp;&nbsp;
   					所属相册:<span id="name"></span>
   				</td>
   			</tr>
   			<tr>
   				<td align="right" colspan="3">
   					名称:${p.pname}&nbsp;&nbsp;
   				</td>
   			</tr>
   			<tr>
   				<td colspan="3">
   					<%
   					for(int i=0;i<photos.size();i++)
   					{
   						if(photos.get(i).getPid()==p.getPid()&&photos.get(i).getAlbumid()==p.getAlbumid())
   						{
   							if(i>0)
   							{%>
   								<a href="ShowSinglePhotoServlet?pid=<%=photos.get(i-1).getPid() %>&id=<%=photos.get(i-1).getAlbumid()%>">上一张</a>
   							<%}	
   							else
   							{%>
   								<a href="ShowSinglePhotoServlet?pid=<%=photos.get(i).getPid() %>&id=<%=photos.get(i).getAlbumid()%>">上一张</a>	
   							<%}
   						}
   					}
   					%>   					
   					<%for(int i=1;i<=photos.size();i++){ %>
   						<!-- 判断当前浏览的照片是否和集合中的某一张照片吻合,如果是同一张,则当前照片的序号变为普通文本 -->
						<%if(p.getPid() != photos.get(i-1).getPid()||p.getAlbumid()!=photos.get(i-1).getAlbumid()){ %>   						
   							<a href="ShowSinglePhotoServlet?pid=<%=photos.get(i-1).getPid() %>&id=<%=photos.get(i-1).getAlbumid()%>">[<%=i%>]</a>
   						<%}else{ %>
   								<%=i%>
   						<%} %>
   					<%} %>
   					<%
   					for(int i=0;i<photos.size();i++)
   					{
   						if(photos.get(i).getPid()==p.getPid()&&photos.get(i).getAlbumid()==p.getAlbumid())
   						{
   							if(i<photos.size()-1)
   							{%>
   								<a href="ShowSinglePhotoServlet?pid=<%=photos.get(i+1).getPid() %>&id=<%=photos.get(i+1).getAlbumid()%>">下一张</a>
   							<%}	
   							else
   							{%>
   								<a href="ShowSinglePhotoServlet?pid=<%=photos.get(i).getPid() %>&id=<%=photos.get(i).getAlbumid()%>">下一张</a>
   							<%}
   						}
   					}
   					%>
   				</td>
   			</tr>
   		</table>
   	</center>
  </body>
  <script>
  	//对图标显示
  	function show(v){
  		if(v == "up"){
  			document.getElementById("up").style.display="block";
  		}else if(v == "down"){
  			document.getElementById("down").style.display="block";
  		}
  	}
  	
  	//对图标隐藏
  	function hide(v){
  		if(v == "up"){
  			document.getElementById("up").style.display="none";
  		}else if(v == "down"){
  			document.getElementById("down").style.display="none";
  		}
  	}
  </script>
</html>
