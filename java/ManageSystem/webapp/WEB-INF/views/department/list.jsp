<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h1>部门管理</h1>
			</section>
			<section class="content">
				<div class="box">
					<!--高级查询--->
					<form class="form-inline" id="searchForm"
						action="#" method="post">
						<input type="hidden" name="currentPage" id="currentPage" value="1">
						<a href="/department?cmd=input" class="btn btn-success inputBtn" style="margin: 10px">
							<span class="glyphicon glyphicon-plus"></span>
							添加
						</a>
					</form>
					<!--编写内容-->
					<div class="box-body table-responsive no-padding ">
						<table class="table table-hover table-bordered">
							<tr>
								<th>编号</th>
								<th>部门名称</th>
								<th>部门编号</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${pageResult.result}" var="d">
								<tr>
									<td>${d.id}</td>
									<td>${d.dep_name}</td>
									<td>${d.dep_id}</td>
									<td>
										<a class="btn btn-info btn-xs inputBtn" href="/department?cmd=input&id=${d.id}">
											<span class="glyphicon glyphicon-pencil"></span>
											编辑
										</a>
										<a href="" onclick="deleteDepartment(${d.id})" class="btn btn-danger btn-xs btn_delete">
											<span class="glyphicon glyphicon-trash"></span>
											删除
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<!--分页-->
						<%@include file="/WEB-INF/views/common/page.jsp"%>
					</div>
				</div>
			</section>
		</div>
		<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
</body>
<script type="text/javascript" src="/js/list.js"></script>
</html>
