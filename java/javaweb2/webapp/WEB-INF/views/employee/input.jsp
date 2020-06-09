<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>员工录入页面</h2>
<form action="/emp/employee?cmd=saveOrUpdate" method="post">
	<input type="hidden" name = "id" value="${e.id}">
	姓名:<input name="name" value="${e.name}"><br/>
	邮箱:<input name="email" value="${e.email}"><br/>
	年龄:<input name="age" value="${e.age}"><br/>
	薪水:<input name="salary" value="${e.salary}"><br/>
	<input type="submit" value="提交">
</form>
</body>
</html>