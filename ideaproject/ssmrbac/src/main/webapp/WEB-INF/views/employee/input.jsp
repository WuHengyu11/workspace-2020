<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工新增或者修改</title>
</head>
<body>
    <form action="/employee/saveOrUpdate.do" method="post">
        <input type="hidden" name="id" value="${employee.id}">
        名称:<input type="text" name="name" placeholder="名称" value="${employee.name}"><br/>
        密码:<input type="text" name="password" placeholder="密码" value="${employee.password}"><br/>
        邮箱:<input type="text" name="email" placeholder="邮箱" value="${employee.email}"><br/>
        年龄:<input type="text" name="age" placeholder="年龄" value="${employee.age}"><br/>
        超管:<input type="text" name="admin" placeholder="超管" value="${employee.admin}"><br/>
        部门:<select name="dept.id">
            <c:forEach items="${departments}" var="department">
                <option value="${department.id}" <c:if test="${employee.dept.id == department.id}">selected</c:if> >${department.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="提交">
    </form>
</body>
</html>


