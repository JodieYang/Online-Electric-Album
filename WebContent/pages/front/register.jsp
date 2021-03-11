<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>邮箱注册</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style type="text/css">
#register {
	width: 450px;
	height: 100px;
	margin: 50px auto;
}

#btn {
	margin-left: 197px;
	margin-top: -24px;
	width: 120px;
	height: 25px;
	font-size: 14px;
	color: #7904c9
}

body {
	background-color: #ecfcf9;
}
</style>


<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		var emailReg=/^([A-Za-z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(emailReg.test($("#useremail").val())){
			$.ajax({
				type:"POST",
				url :"SendEmailServlet?random"+Math.random(),
				data:{
					useremail:$("#useremail").val(),
				},
				success:function(data){
					alert("验证码发送成功，请注意查收。");
				},
			})
		}else{
			alert("邮箱发送失败");
			$("#notice").html("请填写邮箱");
			setTimeout(function(){
				$("#notice").hide();
			},1000);
		}
	}
	);
		//  判断用户是否可以注册
		$("#submit").click(function() {
			if ($("#useremail").val()) {
				$("#RegistForm").submit();
			} else { //  如果没有值
				$("#notice").html("请填写完整信息");
				setTimeout(function() {
					$("#notice").hide();
				}, 1000);
			}
		});
	});
</script>

<script type="text/javascript">
	function check(form)
	{
		var emailReg=/^([A-Za-z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if(form.useremail.value=="")
		{
			alert("请输入邮箱账号！")
			form.useremail.focus();
			return false;
		}
		if(!emailReg.test(form.useremail.value.toString()))
		{
			alert("邮箱格式不正确！")
			form.useremail.focus();
			return false;
		}
		if(form.username.value=="")
		{
			alert("请输入用户名！");
			form.username.focus();
			return false;
		}
		if(form.password.value=="")
		{
			alert("请输入密码！");
			form.password.focus();
			return false;
		}
		return true;
	}
</script>

<script type="text/javascript">
var errors='<%=request.getAttribute("errorsRegister")%>';
<%request.removeAttribute("errorsRegister");%>
if(errors=="usedName")
{
	errors="";
	alert("用户名已存在！");
	
}
if(errors=="failedRegister")
{
	errors="";
	alert("注册失败！");
}
if(errors=="falseCode")
{
	errors="";
	alert("验证码不正确!");
}
</script>
</head>

<body>
	<div class="container">
		<div id="register">
			<form class="form-horizontal" id="RegistForm" method="post"
				action="${pageContext.request.contextPath}/RegisterServlet" onsubmit="return check(this)">
				<fieldset>
					<div id="legend" class="">
						<legend class="">用户注册</legend>
					</div>
					<div class="form-group">
						<!-- Text input-->
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-5">
							<input type="text" placeholder="请输入用户名..." class="form-control"
								required name="username">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-5">
							<input type="password" placeholder="请输入密码..." required
								class="form-control" name="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-5">
							<input type="email" placeholder="请填写邮箱地址..." class="form-control"
								id="useremail" name="useremail" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-5">
							<input type="text" name="code" id="code" placeholder="请输入邮箱的验证码"
								class="form-control" required> 
								<input type="button" name="btn"
								class="btn btn-default" id="btn" value="发送验证码" />
						</div>
					</div>
					<span id="notice" class="hide">请先完成邮箱验证</span><br />
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="pages/front/login.jsp" class="btn btn-success">返回登录</a> <input
								class="btn btn-info" type="submit" id="submit" value="邮箱注册" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html> 