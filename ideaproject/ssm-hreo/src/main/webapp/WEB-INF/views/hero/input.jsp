<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>英雄新增或者修改</title>
</head>
<body>
    <form action="/hero/saveOrUpdate.do" method="post">
        <input type="hidden" name="id" value="${hero.id}">
        名称:<input type="text" name="name" placeholder="名称" value="${hero.name}"><br/>
        昵称:<input type="text" name="nickname" placeholder="昵称" value="${hero.nickname}"><br/>
        血量:<input type="text" name="hp" placeholder="血量" value="${hero.hp}"><br/>
        蓝量:<input type="text" name="mp" placeholder="蓝量" value="${hero.mp}"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>


