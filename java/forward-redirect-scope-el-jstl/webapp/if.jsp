<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- prefix:和别人的标签区分开 -->
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setAttribute("age", 19);
%>
<c:if test="${age >= 18}">
	符合要求可以观看
</c:if>

<c:choose>
	<c:when test="${age >= 80}">不好意思太大</c:when>
	<c:when test="${age < 18}">不好意思太小</c:when>
	<c:otherwise>可以</c:otherwise>
</c:choose>
</body>
</html>