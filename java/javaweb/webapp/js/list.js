function deleteProduct(id){
	var ret = window.confirm("您确定要删除吗?")
	if(ret){
		window.location.href="/pro/product?cmd=delete&id="+id;
	}
}