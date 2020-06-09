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
					<form class="form-horizontal" action="/department?cmd=saveOrUpdate"
						method="post" id="editForm">
						<input type="hidden" name="id" value="${d.id}">
						<div class="form-group" style="margin-top: 10px;">
							<label for="name" class="col-sm-2 control-label">部门名：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name" name="dep_name"
									placeholder="请输入部门名称" value="${d.dep_name}">
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">部门编码：</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="email" name="dep_id"
									placeholder="请输入部门编码" value="${d.dep_id}">
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
