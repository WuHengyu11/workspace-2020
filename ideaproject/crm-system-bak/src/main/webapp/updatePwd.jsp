<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改密码</title>
    <%@include file="/WEB-INF/views/common/link.jsp" %>
    <script src="/js/jquery/jquery.min.js"></script>
    <script>
        $(function () {
            $("#submitBtn").click(() => {
                $.post('/password/updatePwd.do',$("#editForm").serialize(),(data) => {
                    if (data.success){
                        alert("修改成功");
                        window.location.href = ("/login.html");
                    }else{
                       alert(data.msg)
                    }
                })
            })
        })

    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>修改密码</h1>
        </section>
        <section class="content">
            <div class="box" style="padding: 30px;" >
                <span id="errorMsg"></span>
                <form class="form-horizontal" method="post" id="editForm" >
                    <input type="hidden"  name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-3 control-label" >原密码：</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control"  name="oldPassword" id="oldPassword"
                                   placeholder="请输入原密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control"  name="newPassword" id="newPassword"
                                   placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-6">
                            <button id="submitBtn" type="submit" class="btn btn-primary">确定修改</button>
                        </div>
                    </div>
                </form>

            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>
