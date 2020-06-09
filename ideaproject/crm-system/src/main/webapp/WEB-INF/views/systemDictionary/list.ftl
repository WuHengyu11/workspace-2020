
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>字典目录管理</title>
    <#--相对路径-->
    <#include "../common/link.ftl">

    <script>
        $(function () {
            $(".btn-input").click(function () {
                $("input").val();
                var json = $(this).data('json');
                if(json){
                    $("input[name=id]").val(json.id);
                    $("input[name=title]").val(json.title);
                    $("input[name=intro]").val(json.intro);
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
                    $.post('/systemDictionary/delete.do',{id:id},handlerMessage)
                })
            })

        });



    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <#include "../common/navbar.ftl">
    <!--菜单回显 声明变量-->
    <#assign currentMenu="systemDictionary"/>

     <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>字典目录</h1>
        </section>
        <section class="content">
            <div class="box">
                        <!--高级查询--->
                        <form class="form-inline" id="searchForm" action="/systemDictionary/list.do" method="post">
                            <input type="hidden" name="currentPage" id="currentPage" value="1">
                            <a href="#" class="btn btn-success btn-input" style="margin: 10px">
                                <span class="glyphicon glyphicon-plus" ></span> 添加
                            </a>
                        </form>
                        <!--编写内容-->
                        <div class="box-body table-responsive no-padding ">
                            <table class="table table-hover table-bordered">
                                <tr>
                                    <th>编号</th>
                                    <th>标题</th>
                                    <th>编码</th>
                                    <th>简介</th>
                                    <th>操作</th>
                                </tr>
                        <#list pageInfo.list as systemDictionary>
                            <tr><#-- deparment_index索引值从0开始-->
                                <td>${systemDictionary_index+1}</td>
                                <td>${systemDictionary.title!}</td>
                                <td>${systemDictionary.sn!}</td>
                                <td>${systemDictionary.intro!}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${systemDictionary.json}'>
                                        <span class="glyphicon glyphicon-pencil"  ></span> 编辑
                                    </a>
                                    <a href="#" class="btn btn-danger btn-xs btn-delete" data-id="${systemDictionary.id}">
                                        <span class="glyphicon glyphicon-trash" ></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    </div>
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
                <form class="form-horizontal" action="/systemDictionary/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title"
                                   placeholder="请输入字典目录名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">编码：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn"
                                   placeholder="请输入字典目录编码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">简介：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="intro" name="intro"
                                   placeholder="请输入字典目录简介">
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
