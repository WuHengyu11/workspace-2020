function checkAll(checked){
    //将兴趣爱好复选框全选

    //通过name属性获取元素
    var hobby = document.getElementsByName("hobby");

    //操作每个复选框的checked属性值
    for(let i = 0; i < hobby.length; i++){
        hobby[i].checked = checked;
    }
}

function checkUnAll(){
    //通过name属性获取元素
    var hobby = document.getElementsByName("hobby");

    //操作每个复选框的checked属性值
    for(let i = 0; i < hobby.length; i++){
        hobby[i].checked =  !hobby[i].checked;
    }

}

function checkChange(cb){
    //通过name属性获取元素
    var hobby = document.getElementsByName("hobby");

    // 操作每个复选框的checked属性的值
    for (let i = 0;  i < hobby.length; i++){
        hobby[i].checked =  cb.checked;
    }
}