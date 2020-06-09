function deleteDepartment(id){
	var ret = window.confirm("您确定要删除吗?")
	if(ret){
		window.location.href="/department?cmd=delete&id="+id;
	}
}

function deleteEmployee(id){
	var ret = window.confirm("您确定要删除吗?")
	if(ret){
		window.location.href="/employee?cmd=delete&id="+id;
	}
}