
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
                    $("input[name=money]").val(json.money);
                    $("input[name=year]").val(json.year);
                    $("input[name=month]").val(json.month);
                    $("input[name=employee]").val(json.employee);
                    $("input[name=payout]").val(json.payout);
                }
                //弹出对话框
                $("#editModal").modal('show');
            });

            //保存按钮
            $('.btn-submit').click(function () {
               $("#editForm").ajaxSubmit(function (data) {
                   if(data.success){
                       alert("操作成功");
                   }
               })
            });
        });



    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <#include "../common/navbar.ftl">
    <!--菜单回显 声明变量-->
    <#assign currentMenu="department"/>

     <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword"
                               value="${qo.keyword!}" placeholder="请输入姓名">
                    </div>
                    <@shiro.hasAnyRoles name="Admin,Market_Manager">
                        <div class="form-group">
                            <label for="dept"> 发放方式:</label>
                            <select class="form-control" id="payout" name="payoutId">
                                <option value="-1">全部</option>
                                <#list payout as p>
                                    <option value="${p.id}">${p.title}</option>
                                </#list>
                            </select>
                        </div>
                    </@shiro.hasAnyRoles>
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
                        </tr>
                       <#list pageInfo.list as salary>
                          <tr>
                              <td>${salary_index+1}</td>
                              <td>${salary.year!}</td>
                              <td>${salary.month!}</td>
                              <td>${(salary.employee.name)!}</td>
                              <td>${salary.money!}</td>
                              <td>${(salary.payout.title)!}</td>
                             <td>
                          <a href="#" class="btn btn-info btn-xs btn-input" data-json='${salary.json!}'>
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
                <h4 class="modal-title" id="myModalLabel">工资管理新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/salary/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">年份：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="year" name="year"
                                   placeholder="请输入年份">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">月份：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="month" name="month"
                                   placeholder="请输入月份">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">员工：</label>
                        <div class="col-sm-6">
                            <select id="employee" name="employeeId">
                                <#list  employees as e>
                                    <option ${e.id}>${e.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">工资：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="money" name="money"
                                   placeholder="请输入工资">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">发放方式：</label>
                        <div class="col-sm-6">
                           <select id="payout" name="payout_id">
                               <#list payout as p>
                                   <option value="${p.id}">${p.title}</option>
                               </#list>
                           </select>
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
