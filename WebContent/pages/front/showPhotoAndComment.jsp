<%@ page language="java" import="java.util.*,mat.java.beans.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 引入标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <body bgcolor="gray">
  <% 
 	 	List<Photo> photos = (List<Photo>)request.getAttribute("photos");
 	 	List<Comment> plist=(List<Comment>)request.getAttribute("plist");
 	  	Photo p = (Photo)request.getAttribute("p");
  		String userid=request.getSession().getAttribute("userid").toString();
   %>
  	<center>
  		<form action="AddCommentServlet" name="form2" method="post" onsubmit="return checkcontent()">
  		<input type="hidden" name="id" value="${p.albumid}">
   		<input type="hidden" name="pid" value="${p.pid}" >
   		<input type="hidden" name="userid" value="${p.userid}">
   		<table width="250px" height="200px">
   			<tr>
				<th>
				${p.userid}: 
				</th>
				<td>
				${p.pdescription}
				</td>
			</tr>
   			<tr>
   				<td valign="middle" onmouseover="show('up')" onmouseout="hide('up')" width="50px"><a href=""><img id="up" src="img/up.emf" alt="上一张" border="0" style="display:none"></a></td>
   					<th><img src="/photos/${p.userid}/${p.albumid}/${p.pname}" border="0" width="300px" height="300px"/></th>
   				<td valign="middle" onmouseover="show('down')" onmouseout="hide('down')" width="50px"><a href=""><img id="down" src="img/down.emf" alt="下一张" border="0" style="display:none"></a></td>
   			</tr>
   			<tr>
   				<td align="right" colspan="3">
   					分享时间:${p.puploadtime}&nbsp;&nbsp;
   				</td>
   			</tr>
   			<tr>
   				<th colspan="2" style="color:red" id="msg">${msg}</th>
   			</tr>
    		<tr>
   				<td align="right">发表评论:</td>
   				<td><textarea placeholder="说点什么吧..." name="content" rols=2 cols=30></textarea></td>
   				<td><input type="submit" value="发表"></td>
   			</tr>
   			<!-- 如果plist为0，会不会有异常 -->
   			<c:choose>
   			<c:when test="${empty plist}">
   			<tr>
   			<td>暂无评论</td>
   			</tr>
   			</c:when>
   			<c:otherwise>
	   			<c:forEach items="${plist}" var="pinglun" varStatus="num">
	   			<tr>
	   				<th>
	   					${pinglun.cuserid}:
	   				</th>
	   				<!-- 看看这样设置是什么效果 -->
	   				<td colspan="20">
	   					${pinglun.content}
	   				</td>
	   			</tr>
	   			</c:forEach>
	   		</c:otherwise>
	   		</c:choose>
   			<tr>
   				<td colspan="3">
   					<%
   					for(int i=0;i<photos.size();i++)
   					{
   						if(photos.get(i).getPid()==p.getPid()&&photos.get(i).getAlbumid()==p.getAlbumid())
   						{
   							if(i>0)
   							{%>
   								<a href="CommentServlet?userid=<%=photos.get(i-1).getUserid() %>&pid=<%=photos.get(i-1).getPid() %>&id=<%=photos.get(i-1).getAlbumid() %>">上一张</a>
   							<%}	
   							else
   							{%>
   								<a href="CommentServlet?userid=<%=photos.get(i).getUserid() %>&pid=<%=photos.get(i).getPid() %>&id=<%=photos.get(i).getAlbumid() %>">上一张</a>	
   							<%}
   						}
   					}
   					%>
   					<%for(int i=1;i<=photos.size();i++){ %>
   						<!-- 判断当前浏览的照片是否和集合中的某一张照片吻合,如果是同一张,则当前照片的序号变为普通文本 -->
						<%if(p.getPid() != photos.get(i-1).getPid()||p.getAlbumid()!=photos.get(i-1).getAlbumid()){ %>   						
   							<a href="CommentServlet?userid=<%=photos.get(i-1).getUserid() %>&pid=<%=photos.get(i-1).getPid() %>&id=<%=photos.get(i-1).getAlbumid()%>">[<%=i%>]</a>
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
   								<a href="CommentServlet?userid=<%=photos.get(i+1).getUserid() %>&pid=<%=photos.get(i+1).getPid() %>&id=<%=photos.get(i+1).getAlbumid() %>">下一张</a>
   							<%}	
   							else
   							{%>
   								<a href="CommentServlet?userid=<%=photos.get(i).getUserid() %>&pid=<%=photos.get(i).getPid() %>&id=<%=photos.get(i).getAlbumid()%>">下一张</a>
   							<%}
   						}
   					}
   					%>

   				</td>
   			</tr>
   		</table>
   	</form>
   	</center>
  </body>
  <!-- 验证输入的评论是否为空 -->
  <script>
 	var form2 = document.form2;
	//获取错误信息显示位置的对象
	var msg = document.getElementById("msg");
	function checkcontent(){
		//获取用户名
		var content = form2.content.value;
  		if(content.length<1){
  			msg.innerText="请输入您的评论";
  			return false;
  		}else{
  			msg.innerText="";
  			return true;
  		}
    }
  </script>
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
