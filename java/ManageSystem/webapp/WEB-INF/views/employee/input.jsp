<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>部门管理</title>
<jsp:include page="/WEB-INF/views/common/link.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@include file="/WEB-INF/views/common/navbar.jsp"%>
		<!--菜单回显-->
		<c:set var="currentMenu" value="department" />
		<%@include file="/WEB-INF/views/common/menu.jsp"%>
		<div class="content-wrapper">
			<section class="content-header">
				<h1>部门编辑</h1>
			</section>
			<section class="content">
				<div class="box">
					<span Style="color:red">${errorMsg}</span>
					<form class="form-horizontal" action="/employee?cmd=saveOrUpdate"
						method="post" id="editForm" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${e.id}">
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">员工姓名：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="请输入员工姓名" value="${e.name}">
							</div>
						</div>
						
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">员工邮箱：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="email"
									placeholder="请输入员工邮箱" value="${e.email}">
							</div>
						</div>
						
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">员工年龄：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="age"
									placeholder="请输入员工年龄" value="${e.age}">
							</div>
						</div>
						
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">员工薪水：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="salary"
									placeholder="请输入员工年龄" value="${e.salary}">
							</div>
						</div>
						
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">图片：</label>
							<div class="col-sm-6">
								<input type="file" class="form-control" id="img" name="img">
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-6">
								<button id="submitBtn" type="submit" class="btn btn-primary">保存</button>
								<button type="reset" class="btn btn-danger">重置</button>
							</div>
						</div>
					</form>

					<br />
				</div>
			</section>
		</div>
		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>

</body>
</html>
