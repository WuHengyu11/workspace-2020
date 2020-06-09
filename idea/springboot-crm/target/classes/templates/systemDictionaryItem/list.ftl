
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

                //获取左侧字典目录的标题，设置到模态框中
                $("input[name=parentName]").val($("#${qo.parentId}").text());
                $("input[name=parentId]").val(${qo.parentId});


                var json = $(this).data('json');
                if(json){
                    $("input[name=id]").val(json.id);
                    $("input[name=title]").val(json.title);
                    $("input[name=sequence]").val(json.sequence);
                }
                if(${qo.parentId}>0){
                    //弹出对话框
                    $("#editModal").modal('show');
                }else{
                    $.messager.alert("温馨提示","请先选中目录")
                }
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
                    $.post('/systemDictionaryItem/delete.do',{id:id},handlerMessage)
                })
            })

        });



    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <#include "../common/navbar.ftl">
    <!--菜单回显 声明变量-->
    <#assign currentMenu="systemDictionaryItem"/>

     <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>字典明细</h1>
        </section>
        <section class="content">
            <div class="box">

                <div class="row">
                    <div class="col-md-3">
                        <div class="panel panel-default">
                            <div class="panel-heading">字典目录</div>
                            <div class="panel-body">
                                <div class="list-group">
                                    <#list systemDictionarys as d>
                                    <a id="${d.id}" href="/systemDictionaryItem/list?parentId=${d.id}"
                                       class="list-group-item ">${d.title}
                                    </a>
                                    </#list>
                                    <script>
                                        $("#${qo.parentId}").addClass("active");
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <!--高级查询--->
                        <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list" method="post">
                            <input type="hidden" name="currentPage" id="currentPage" value="1">
                            <input type="hidden" name="parentId" value="${qo.parentId}" >
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
                                    <th>序号</th>
                                    <th>操作</th>
                                </tr>
                        <#list result.list as systemDictionaryItem>
                            <tr><#-- deparment_index索引值从0开始-->
                                <td>${systemDictionaryItem_index+1}</td>
                                <td>${systemDictionaryItem.title!}</td>
                                <td>${systemDictionaryItem.sequence!}</td>
                                <td>
                                    <a href="#" class="btn btn-info btn-xs btn-input" data-json='${systemDictionaryItem.json}'>
                                        <span class="glyphicon glyphicon-pencil"  ></span> 编辑
                                    </a>
                                    <a href="#" class="btn btn-danger btn-xs btn-delete" data-id="${systemDictionaryItem.id}">
                                        <span class="glyphicon glyphicon-trash" ></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                        </div>

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
<!-- Modal模态框 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate" method="post"
                      id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="intro" class="col-sm-3 control-label">字典目录：</label>
                        <div class="col-sm-6">
                            <!-- 用来给用户看的 -->
                            <input type="text" class="form-control" id="parentName" name="parentName" readonly>
                            <!-- 用来提交到后台关联的 -->
                            <input type="hidden"  name="parentId">

                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="title" class="col-sm-3 control-label">明细标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title"
                                   placeholder="请输入明细标题">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">明细序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence"
                                   placeholder="请输入明细编码">
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
