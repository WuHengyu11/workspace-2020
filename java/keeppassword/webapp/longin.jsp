<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/kep/keeppassword" method="post">
	账号:<input type="text" name="username" value="${cookie.username.value}"><br>
	密码:<input type="password" name="password" value="${cookie.password.value}"><br>
	记住密码:<input type="checkbox" name="remember" ${cookie.remember.value == 'on' ? 'checked="checked"' : ''}><br>
	<a>${remember}</a>
	<input type="submit" value="登录">
</form>

</body>
</html>