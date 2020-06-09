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
	<form action="/product">
		名称:<input name="productName">
		最低零售价:<input name="minSalePrice">
		最高零售价:<input name="maxSalePrice">
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
		<c:forEach items="${pageResult.result}"  var="productList">
			<tr>
					<td>${productList.id}</td>
					<td>${productList.productName}</td>
					<td>${productList.dir_id}</td>
					<td>${productList.salePrice}</td>
					<td>${productList.supplier}</td>
					<td>${productList.brand}</td>
					<td>${productList.cutoff}</td>
					<td>${productList.costPrice}</td>
					<td>
						<a href="" onclick="deleteProduct(${productList.id})">删除</a>
						<a href="/pro/product?cmd=input&id=${productList.id}">编辑</a>
					</td>
				</tr>
		</c:forEach>
		<tr align="left">
			<td colspan="9">
				<a href="/product?currentPage=1">首页</a>
				<a href="/product?currentPage=${pageResult.prevPage}">上一页</a>
				<a href="/product?currentPage=${pageResult.nextPage}">下一页</a>
				<a href="/product?currentPage=${pageResult.totalPage}">尾页</a>
				当前:${pageResult.currentPage}/${pageResult.totalPage}
				总条数
				共${pageResult.totalPage}页
			</td>
		</tr>
	</table>
</body>

</html>