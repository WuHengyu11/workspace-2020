<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工信息表</title>
</head>
<body>
	<h2>员工信息表</h2>
	<a href="/emp/employee?cmd=input">添加</a>
	<table border=1px>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>邮箱</th>
			<th>年龄</th>
			<th>薪水</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${e}" var="e">
			<tr>
				<td>${e.id}</td>
				<td>${e.name}</td>
				<td>${e.email}</td>
				<td>${e.age}</td>
				<td>${e.salary}</td>
				<td>
						<a href="" onclick="deleteEmployee(${e.id})">删除</a>
						<a href="/emp/employee?cmd=input&id=${e.id}">编辑</a>
					</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript" src="/emp/js/list.js"></script>
</html>