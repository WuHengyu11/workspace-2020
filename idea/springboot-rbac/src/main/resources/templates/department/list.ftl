
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
   <#include "../common/link.ftl">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
   <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <c:set var="currentMenu" value="department"/>
   <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="/department/input" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        <#list result.list as department>
                            <tr>
                                <td>${department_index+1}</td>
                                <td>${department.name}</td>
                                <td>${department.sn}</td>
                                <td>
                                    <a href="/department/input?id=${department.id}" class="btn btn-info btn-xs btn-input">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="/department/delete?id=${department.id}" class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                   <#include "../common/page.ftl">
                    <%--<div style="text-align: center;">
                        <ul id ="pagination-demo" class="pagination-sm"></ul>
                    </div>
                    <script>
                        $('#pagination-demo').twbsPagination({
                            totalPages: ${pageResult.totalPage}, //一页显示的条数
                            visiblePages:6, //可显示的页数
                            startPage:${pageResult.currentPage},
                            first:"首页",
                            prev:"上页",
                            next:"下页",
                            last:"尾页",
                            onPageClick:function(event, page){  //点击页数就会执行
                                $('#currentPage').val(page);
                                $('#searchForm').submit();
                            } });
                    </script>--%>
                </div>
            </div>
        </section>
    </div>
   <#include "../common/footer.ftl">
</div>
</body>
</html>
