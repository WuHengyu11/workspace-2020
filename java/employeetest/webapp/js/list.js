function deleteEmployee(id){
	var ret = window.confirm("您确定要删除吗?")
	if(ret){
		window.location.href="/emp/employee?cmd=delete&id="+id;
	}
}