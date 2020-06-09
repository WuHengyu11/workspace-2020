<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门新增或者修改</title>
</head>
<body>
    <form action="/department/saveOrUpdate.do" method="post">
        <input type="hidden" name="id" value="${dept.id}">
        <input type="text" name="name" placeholder="名称" value="${dept.name}"><br/>
        <input type="text" name="sn" placeholder="缩写" value="${dept.sn}"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
