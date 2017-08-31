<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>登录</title>
</head>
<body>
	<div>
		<form action="${pageContext.request.contextPath }/login" method="post">
			<div>
				<span>用户名：</span><input type="text" id="username" name="username">
			</div>
			<div>
				<span>密码：</span><input type="password" id="password" name="password">
			</div>
			<div>
				<button type="submit" value="登录">登录</button>
			</div>
		</form>
		<span>${error }</span>

	</div>

</body>
</html>