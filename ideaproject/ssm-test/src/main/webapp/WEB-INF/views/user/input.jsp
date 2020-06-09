<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户新增或者修改</title>
</head>
<body>
    <form action="/user/saveOrUpdate.do" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <input type="text" name="username" placeholder="名称" value="${user.username}"><br/>
        <input type="text" name="email" placeholder="邮箱" value="${user.email}"><br/>
        <input type="text" name="age" placeholder="年龄" value="${user.age}"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
