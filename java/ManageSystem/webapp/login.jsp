<%--
  Created by IntelliJ IDEA.
  User: XYQ28
  Date: 2019/9/28
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>综合管理系统-登录</title>
    <link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/js/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/js/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/fonts.googleapis.com.css">

    <script src="/js/jquery/jquery.min.js"></script>
    <script src="/js/bootstrap/js/bootstrap.js"></script>
    <script src="/js/adminlte/js/adminlte.min.js"></script>
    <script src="/js/plugins/twbsPagination/jquery.twbsPagination.min.js"></script>
    <script src="/js/system/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {

        });
    </script>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="http://www.wolfcode.cn"><b>叩丁狼</b>CRM</a>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">请输入账号密码</p>
        <span style="color: red">${errorMsg}</span>
        <form method="post" action="/login">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="请输入账号" name="username" value="${cookie.username.value}">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="请输入密码" name="password" value="${cookie.password.value}">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <script type="text/javascript">
            	window.onload = function(){
            		document.getElementById("randomCode").onclick = function(){
            			this.src="/randomCode?" + new Date().getTime()
            		}
            	}
            </script>
            <div class="form-group has-feedback">
                	<input type="text"  name="randomCode" placeholder="请输入验证码" style="height:34px">
                	<img alt="" src="/randomCode" id="randomCode" style="height:34px">
            </div>
            <div class="form-group has-feedback">
                	记住密码:<input type="checkbox"  name="rememberMe" ${cookie.rememberMe.value == 'on' ? 'checked="checked"' : ''}>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat submitBtn">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
