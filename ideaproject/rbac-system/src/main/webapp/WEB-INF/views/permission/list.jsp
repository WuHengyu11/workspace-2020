<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp"%>
    <script>
        $(function () {
            $(".btn_reload").click(() =>{
                $.get('/permission/reload.do',(data) =>{
                    if(data.success){//加载成功
                        alert("加载成功");
                        window.location.reload();//重新加载当前页面
                    }else {
                        alert(data.msg);//弹出错误提示
                    }
                })
            })
        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp"%>
    <!--菜单回显-->
    <c:set var="currentMenu" value="permission"/>
    <%@include file="/WEB-INF/views/common/menu.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box" >
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="javascript:;" class="btn btn-success btn_reload" style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span>  重新加载
                    </a>
                </form>

                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${pageResult.data}" var="permission" varStatus="vs">
                       <tr>
                           <td>${vs.count}</td>
                           <td>${permission.name}</td>
                           <td>${permission.expression}</td>
                           <td>
                               <a href="/permission/delete.do?id=${permission.id}" class="btn btn-danger btn-xs btn_delete" >
                                   <span class="glyphicon glyphicon-trash"></span> 删除
                               </a>
                           </td>
                       </tr>
                    </c:forEach>
                    </table>
                <!--分页-->
                <%@include file="/WEB-INF/views/common/page.jsp"%>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</div>
</body>
</html>
