<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <script>
        function goPage(currentPage){
            document.getElementById("currentPage").value=currentPage;
            document.forms[0].submit();
        }
    </script>
</head>
<body>
    <a href="/user/input.do">新增</a>

    <!-- 查询的表单 -->
    <form action="/user/list.do" method="post">
        <input type="hidden" name="currentPage" id="currentPage" value="1"/>
        关键字：<input type="text" name="keyword" value="${qo.keyword}"/>
        年龄范围:<input type="text" name="minAge" value="${qo.minAge}"/> ~ <input type="text" name="maxAge" value="${qo.maxAge}"/>
        <input type="submit" value="查询"/>
    </form>

    <table width="800px" cellpadding="0" cellspacing="0" align="center" border="1px">
        <tr>
            <td>ID</td>
            <td>名称</td>
            <td>邮箱</td>
            <td>年龄</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${pageResult.data}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>
                    <a href="/user/delete.do?id=${user.id}">删除</a>
                    <a href="/user/input.do?id=${user.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        <tr align="center">
            <td colspan="9">
                <a href="javascript:goPage(1);">首页</a>
                <a href="javascript:goPage(${pageResult.prevPage});">上一页</a>
                <a href="javascript:goPage(${pageResult.nextPage});">下一页</a>
                <a href="javascript:goPage(${pageResult.totalPage});">尾页</a>
                当前:${pageResult.currentPage}/${pageResult.totalPage}
                总条数:${pageResult.totalCount}
            </td>
        </tr>
    </table>
</body>
</html>
