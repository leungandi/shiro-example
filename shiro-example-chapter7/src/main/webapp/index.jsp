<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath", contextPath);
%>
<html>
<head>
<title>首页</title>
</head>
<body>

	<a href="${contextPath}/login">登录</a>
	<br />
	<a href="${contextPath}/authenticated">已身份认证</a>
	<br />
	<a href="${contextPath}/role">角色授权</a>
	<br />
	<a href="${contextPath}/permission">权限授权</a>
	<br />

</body>
</html>