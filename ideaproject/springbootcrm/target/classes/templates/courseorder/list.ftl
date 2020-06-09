<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>课程订单管理</title>
    <#--相对路径-->
    <#include "../common/link.ftl">
    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("#editForm").val();
                var json = $(this).data('json');
                if(json){
                    $("#editForm input[name=id]").val(json.id);
                    $("#editForm select[name='customer.id']").val(json.customerId);
                    $("#editForm select[name='course.id']").val(json.courseId);
                    $("#editForm input[name=money]").val(json.money);
                }
                //弹出对话框
                $("#editModal").modal('show');
            });

            //保存按钮
            $('.btn-submit').click(function () {
               $("#editForm").ajaxSubmit('/courseorder/saveOrUpdate.do',function (data) {
                   console.log(data);
                   if (data.success){
                       $.messager.alert("温馨提示","操作成功,2秒后自动刷新页面");
                       setTimeout(function () {
                           window.location.reload()
                       },2000)
                   }else{
                       $.messager.popup(data.msg);
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
    <#assign currentMenu="courseorder"/>

     <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>课程订单管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/courseorder/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword"
                               value="${qo.keyword!}" placeholder="请输入姓名">
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
                            <th>客户名称</th>
                            <th>销售课程</th>
                            <th>销售时间</th>
                            <th>销售金额</th>
                            <th>操作</th>
                        </tr>
                        <#list pageInfo.list as courseorder>
                            <tr><#-- courseorder_index索引值从0开始-->
                                <td>${courseorder_index+1}</td>
                                <td>${(courseorder.customer.name)!}</td>
                                <td>${(courseorder.course.title)!}</td>
                                <td>${(courseorder.inputTime)!}</td>
                                <td>${courseorder.money!}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${courseorder.json!}'>
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
                <form class="form-horizontal" action="/courseorder/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">选择客户：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="customer.id">
                                <#list customers as c>
                                    <option value="${c.id}">${c.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">选择课程：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="course.id">
                                <#list items as items>
                                    <option value="${items.id}">${items.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">销售金额：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="money" name="money"
                                   placeholder="请输入销售金额">
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
