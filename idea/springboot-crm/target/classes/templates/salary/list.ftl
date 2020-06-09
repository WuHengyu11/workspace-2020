
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>工资管理</title>
    <#--相对路径-->
    <#include "../common/link.ftl">

    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("input").val();
                var json = $(this).data('json');
                if(json){
                    $("input[name=id]").val(json.id);
                    $("input[name=name]").val(json.name);
                    $("input[name=sn]").val(json.sn);
                }
                //弹出对话框
                $("#editModal").modal('show');
            });

            //保存按钮
            $('.btn-submit').click(function () {
               $("#editForm").ajaxSubmit(handlerMessage)
            });
            //删除操作
            $(".btn-delete").click(function (data) {
                var id = $(this).data('id');
                $.messager.confirm("警告","是否确认删除?",function () {
                    //发送ajax请求
                    $.post('/salary/delete.do',{id:id},handlerMessage)
                })
            })

        });



    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <#include "../common/navbar.ftl">
    <!--菜单回显 声明变量-->
    <#assign currentMenu="salary"/>

     <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/salary/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword" value="${qo.keyword!}" placeholder="请输入姓名/邮箱">
                    </div>
                    <div class="form-group">
                        <label for="dept"> 部门:</label>
                        <select class="form-control" id="dept" name="deptId">
                            <option value="-1">全部</option>
                                <#list  payout as p>
                                    <option value="${p.id}">${p.title}</option>
                                </#list>
                        </select>
                        <script>
                            $("#dept option[value='${qo.dept_id!}']").prop("selected", true);
                        </script>
                    </div>
                    <button id="btn_query" class="btn btn-primary">
                        <span class="glyphicon glyphicon-search">

                        </span> 查询
                    </button>
                    <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus" ></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>年份</th>
                            <th>月份</th>
                            <th>员工</th>
                            <th>工资</th>
                            <th>发放方式</th>
                            <th>操作</th>
                        </tr>
                        <#list result.list as salary>
                            <tr><#-- deparment_index索引值从0开始-->
                                <td>${salary_index+1}</td>
                                <td>${salary.year!}</td>
                                <td>${salary.month!}</td>
                                <td>${(salary.seller.username)!}</td>
                                <td>${salary.money!}</td>
                                <td>${(salary.source.title)!}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" >
                                        <span class="glyphicon glyphicon-pencil"  ></span> 编辑
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                     <#include "../common/page.ftl">
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>

<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/salary/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入部门名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn"
                                   placeholder="请输入部门编码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary btn-submit">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
