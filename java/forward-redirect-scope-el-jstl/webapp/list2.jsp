<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>学生信息列表</h2>
	${students[0]}
	<br/>
	${students[0].id}
	<br/>
	${students[0].name}
	<br/>
	${students[0].age}
	<br/>
	${students[0].favs[1]}
	<br/>
</body>
</html>