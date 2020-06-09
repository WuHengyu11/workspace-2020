function moveAll(srcSelect,distSelect){
    //将左下拉框中的所有option元素追加到右下拉框中
    //获取所有option
    var srcSelect = document.getElementById(srcSelect);
    var distSelect = document.getElementById(distSelect);

    //获取select1中的option
    var options = srcSelect.getElementsByTagName("option");
    for (var i = 0; i < options.length; ) {
        distSelect.appendChild(options[i]); 
    }
}

function moveSelected(srcSelect, distSelect){
    //将选中的option进行移动

    //获取元素
    var srcSelect = document.getElementById(srcSelect);
    var distSelect = document.getElementById(distSelect);

    //获取option
    var options = srcSelect.getElementsByTagName("option");

    for (var i = 0; i < options.length; i++) {
        //如果option元素被选中,那么他的selected属性值为true
         if(options[i].selected){
              distSelect.appendChild(options[i]);
               i--;
         } 
    }
}