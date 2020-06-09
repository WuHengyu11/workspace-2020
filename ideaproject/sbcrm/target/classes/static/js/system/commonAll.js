$(function () {
    $.messager.model = {
        ok: {text:'确认'},
        cancel: {text:'取消'}
    };
});
function handleMessage(data) {
    if (data.success){
        $.messager.alert("温馨提示","操作成功,2秒后自动刷新页面");
        setTimeout(function () {
            window.location.reload()
        },2000)
    }else{
        $.messager.popup(data.msg);
    }
}

//提交数据时不需要添加[]
jQuery.ajaxSettings.traditional = true;