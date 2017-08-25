<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>登录成功</title>
</head>
<body>
	<div>
		欢迎你,${username }	
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/logout">退出</a>
	</div>
	<a href="${pageContext.request.contextPath }">go to index</a>
</body>
</html>