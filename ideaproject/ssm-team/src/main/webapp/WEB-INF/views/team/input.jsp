<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>团队新增或者修改</title>
</head>
<body>
    <form action="/team/saveOrUpdate.do" method="post">
        <input type="hidden" name="id" value="${team.id}">
        团队名称:<input type="text" name="name" placeholder="名称" value="${team.name}"><br/>
        团队简写:<input type="text" name="abbr" placeholder="简写" value="${team.abbr}"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>


