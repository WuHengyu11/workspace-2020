<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/pro/js/list.js"></script>
</head>
<body>
	<h2>商品信息列表</h2>
	<a href="/pro/product?cmd=input">添加</a>
	<form action="/filter" method="post">
		<input  type="hidden" name="currentPage" id="currentPage">
		货品名称:<input type="text" name="productName" value="${qo.productName}">
		最低零售价:<input type="text" name="minPrice" value="${qo.minPrice}">
		最高零售价:<input type="text" name="maxPrice" value="${qo.maxPrice}">
	<input type="submit" value="查询">
	</form>
	<table border="1" >
		<tr>
			<th>编号</th>
			<th>商品名称</th>
			<th>分类</th>
			<th>售价</th>
			<th>供应商</th>
			<th>品牌</th>
			<th>折扣</th>
			<th>进价</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageResult.result}"  var="p">
			<tr>
					<td>${p.id}</td>
					<td>${p.productName}</td>
					<td>${p.dir_id}</td>
					<td>${p.salePrice}</td>
					<td>${p.supplier}</td>
					<td>${p.brand}</td>
					<td>${p.cutoff}</td>
					<td>${p.costPrice}</td>
					<td>
						<a href="" onclick="deleteProduct(${productList.id})">删除</a>
						<a href="/pro/product?cmd=input&id=${productList.id}">编辑</a>
					</td>
				</tr>
		</c:forEach>
			<tr align="center">
					<td colspan="9">
						<a href="javascript:goPage(1);">首页</a>&nbsp;&nbsp;
						<a href="javascript:goPage(${pageResult.prevPage});">上一页</a>&nbsp;&nbsp;
						<a href="javascript:goPage(${pageResult.nextPage});">下一页</a>&nbsp;&nbsp;
						<a href="javascript:goPage(${pageResult.totalPage});">尾页</a>&nbsp;&nbsp;
						当前第${pageResult.currentPage}/${pageResult.totalPage}页&nbsp;&nbsp;
						共${pageResult.totalPage}页&nbsp;&nbsp;
						共${pageResult.rows}条
					</td>
				</tr>
	</table>
</body>
<script type="text/javascript">
	function goPage(currentPage){
		//把值设置给表单元素
		document.getElementById("currentPage").value=currentPage;
		//提交表单
		document.forms[0].submit();
	}
</script>
</html>