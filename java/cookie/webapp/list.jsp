<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>邮件列表</h2>
<!-- 
	在JSP中获取Cookie信息,使用EL的内置对象
 -->
当前用户:${cookie.username.value}
<a href="/log/content.jsp">新邮件1</a>
<a href="/log/content.jsp">新邮件2</a>
<a href="/log/content.jsp">新邮件3</a>
<a href="/log/content.jsp">新邮件4</a>
</body>
</html>