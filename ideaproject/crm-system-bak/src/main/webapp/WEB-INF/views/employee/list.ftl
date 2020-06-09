<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <script src="/js/jquery/jquery.min.js"></script>
    <script>
        $(function () {
            $("#allSelect").click(function () {
               //把当前全选的复选框的checked状态,设置到列表里面的所有的复选框中
                $(".cb").prop('checked',$(this).prop("checked"));
            });
            //列表中的复选框
            $(".cb").click(function () {
                //判断列表中是否全部勾选,如果是也需要设置allSelect勾选状态

               $("#allSelect").prop('checked', $(".cb:checked").length == $(".cb").length)
            });

            //批量删除
            $(".btn-batchDelete").click(function () {
                //判断是否已经选中数据
                var cbs = $(".cb:checked");
                //如果没有选中则需要提示
                if (cbs.length == 0) {
                    $.messager.alert("警告","请先选中数据");
                    return;
                }
                //提示确认框
                $.messager.confirm('警告','是否删除该数据?',function () {
                    //获取选中的数据发ajax提交到后台
                    var ids = [];
                    cbs.each(function (index,ele) {
                        var id = $(ele).data('id');
                        ids.push(id);
                    });
                    $.post('/employee/batchDelete.do',{ids:ids},handleMessage)
                });
            });

            //表单数据验证
            $("#editForm").bootstrapValidator({
                fields: {
                    name:{
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    }
                }
            })
        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl"/>
    <#include "../common/link.ftl">
    <!--菜单回显-->
    <#assign current="employee">
    <#include "../common/menu.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入姓名/邮箱">
                        </div>
                        <div class="form-group">
                            <label for="dept"> 部门:</label>
                            <select class="form-control" id="dept" name="deptId">
                                <option value="-1">全部</option>
                                <#list departments as d>
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                            <script>
                                $("#dept option[value='${qo.deptId}']").prop("selected", true);
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                        <a href="/employee/input.do" class="btn btn-success btn_redirect">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                        <a href="#" class="btn btn-danger btn_redirect btn-batchDelete">
                            <span class="glyphicon glyphicon-trash"></span> 批量删除
                        </a>
                    </form>
                </div>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="allSelect"></th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>email</th>
                        <th>年龄</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <#list pageInfo.list as employee>
                        <tr>
                            <td><input type="checkbox" class="cb" data-id="${employee.id}"></td>
                            <td>${employee_index+1}</td>
                            <td>${employee.name}</td>
                            <td>${employee.email}</td>
                            <td>${employee.age}</td>
                            <td>${(employee.dept.name)!}</td>
                            <td>
                                <a href="/employee/input.do?id=${employee.id}" class="btn btn-info btn-xs btn_redirect">
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                                <a href="/employee/delete.do?id=${employee.id}" class="btn btn-danger btn-xs btn_delete">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                                <a href="/password/input.do?id=${employee.id}" class="btn btn-danger btn-xs btn_delete"  id="reset">
                                    <span class="glyphicon glyphicon-trash"></span> 重置密码
                                </a>
                            </td>
                        </tr>
                    </#list>
                </table>
                <!--分页-->
                <#include "../common/page.ftl"/>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl"/>
</div>
</body>
</html>
