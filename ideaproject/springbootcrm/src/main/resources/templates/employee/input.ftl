<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl" >
    <script>
        //下拉菜单选项移动
        function moveSelected(src, tar) {
            $('.' + tar).append($('.' + src + ' option:selected')); //选中即 seleced
        }

        function moveAll(src, tar) {
            $('.' + tar).append($('.' + src + ' option'));
        }

        $(function () {
            var role;
            $('#admin').click(function () {
                var checked = $(this).prop('checked'); //删除所选区域
                if (checked) {
                    role = $('#role').detach();  //恢复所选区域
                } else {
                    $('#adminDiv').after(role);
                }
            });
            //判断,如果选中则删除选中区域
            var checked = $('#admin').prop('checked');
            if (checked) {
                role = $('#role').detach();
            }

            /*//表单提交处理
            $('#submitBtn').click(function () {
                //设置为选中
                $('.selfRoles option').prop('selected',true);
                //提交
                $('#editForm').submit();
            })*/
            
            $('.selfRoles  option').each(function (i,ele) {
                var arr = [];
                arr.push($(ele).val());

                $('.allRoles option').each(function (i,ele) {
                    var arr1 = $(ele).val();
                   if($.inArray(arr1,arr)> -1){
                       $(ele).remove();
                   }
                });
            });
            $("#editForm").bootstrapValidator({
                feedbackIcons: { //图标
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields:{ //配置要验证的字段
                    <#if !employee??>
                    name:{
                        validators:{ //验证的规则
                            remote: { //远程验证
                                type: 'POST', //请求方式
                                message: '用户名已经存在', //验证不通过时的提示信息
                                delay: 2000 //输入内容2秒后发请求
                            },
                            notEmpty:{ //不能为空
                                message:"用户名必填" //错误时的提示信息
                            },
                            stringLength: { //字符串的长度范围
                                min: 1,
                                max: 5
                            }
                        }

                    },
                    </#if>
                    password:{
                        validators:{
                            notEmpty:{ //不能为空
                                message:"密码必填" //错误时的提示信息
                            }
                        }
                    },
                    repassword:{
                        validators:{
                            notEmpty:{ //不能为空
                                message:"密码必填" //错误时的提示信息
                            },
                            identical: {//两个字段的值必须相同
                                field: 'password',
                                message: '两次输入的密码必须相同'
                            }
                        }
                    },
                    email: {
                        validators: {
                            emailAddress: {} //邮箱格式
                        }
                    },
                    age:{
                        validators: {
                            between: { //数字的范围
                                min: 18,
                                max: 60
                            }
                        }
                    }
                }
            })


        });
    </script>


</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl" >
    <!--菜单回显-->
    <#assign currentMenu="employee"/>
    <#include "../common/menu.ftl" >
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(employee.id)!}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(employee.name)!}"
                                   placeholder="请输入用户名"
                            <#if employee??>
                                readonly
                            </#if>
                            >
                        </div>
                    </div>

                    <#if !employee??>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="repassword" name="repassword"
                                       placeholder="再输入一遍密码">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" value="${(employee.email)!}"
                                   placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${(employee.age)!}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <#list departments as d >
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <script>
                        $('#dept').val(${(employee.dept.id)!})
                    </script>
                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox">
                             <#if employee?? && employee.admin >
                                <script>
                                    $("#admin").prop("checked", true);
                                </script>
                             </#if>
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <#list roles as role>
                                        <option value="${role.id}">${role.name}</option>
                                    </#list>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <#list (employee.roles)! as role>
                                        <option value="${(role.id)!}">${(role.name)!}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="submit" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>

            </div>
        </section>
    </div>
    <#include "../common/footer.ftl" >
</div>
</body>
</html>
