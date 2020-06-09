<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>商品录入页面</h2>
<form action="/pro/product?cmd=saveOrUpdate" method="post">
	<input type="hidden" name = "id" value="${p.id}">
	名称:<input name="productName" value="${p.productName}"><br/>
	零售价:<input name="salePrice" value="${p.salePrice}"><br/>
	供应商:<input name="supplier" value="${p.supplier}"><br/>
	品牌:<input name="brand" value="${p.brand}"><br/>
	折扣:<input name="cutoff" value="${p.cutoff}"><br/>
	成本价:<input name="costPrice" value="${p.costPrice}"><br/>
	分类:
	<select name="dir_id">
		<option value="1" ${p.dir_id == 1?'selected="selected"':' '}>键盘</option>
		<option value="2" ${p.dir_id == 2?'selected="selected"':' '}>鼠标</option>
		<option value="3" ${p.dir_id == 3?'selected="selected"':' '}>纸巾</option>
	</select>
	<input type="submit" value="提交">
</form>
</body>
</html>