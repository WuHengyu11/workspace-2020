<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>部门列表</title>
    <script>
        function goPage(currentPage){
            document.getElementById("currentPage").value=currentPage;
            document.forms[0].submit();
        }
    </script>
</head>
<body>
    <a href="/department/input.do">新增</a>

    <!-- 查询的表单 -->
    <form action="/department/list.do" method="post">
        <input type="hidden" name="currentPage" id="currentPage" value="1"/>
    </form>

    <table width="800px" cellpadding="0" cellspacing="0" align="center" border="1px">
        <tr>
            <td>ID</td>
            <td>名称</td>
            <td>缩写</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${pageResult.data}" var="department">
            <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td>${department.sn}</td>
                <td>
                    <a href="/department/delete.do?id=${department.id}">删除</a>
                    <a href="/department/input.do?id=${department.id}">修改</a>
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
