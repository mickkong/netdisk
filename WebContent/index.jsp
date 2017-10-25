<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${basePath}js/jquery-3.2.1.min.js"></script>
<title>登录</title>
</head>
<body>
	=${basePath }= 用户名：
	<input id="username" name="username" type="text" /> 密码：
	<input id="password" name="password" type="password" />
	<input type="button" value="登录" onclick="login()" />
	<label id="msg"></label>
	<a href="#">忘记密码？</a>
	<a href="#">注册</a>
	<script type="text/javascript">
		function login() {
			var username = $("#username").val();
			var password = $("#password").val();
			$.post("user/login?username=" + username + "&password=" + password,
					function(msg) {
						if (msg == 'success') {
							location.href = "${basePath}jsp/list.jsp";
						} else {
							$("#msg").text(msg);
						}
					});
		}
	</script>
</body>
</html>