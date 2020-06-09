window.onload = function(){
    var div1 = document.getElementById("div1");

    //访问属性 元素对象.属性名
    console.log(div1.id);

    //如果属性名中包含一些特殊字符(.-)
    console.log(div1["xxx.ooo"]);
    console.log(div1.getAttribute("id"));
    console.log(div1.getAttribute("class"));

    //class属性,如果使用.去访问的时候,名称className
    console.log(div1.className);
    //操作元素的样式中的属性
    console.log(div1.style["background-color"]);
    console.log(div1.style.backgroundColor);

    //属性名和属性值相同的属性,此时使用JS获取到的属性值是true/false
    var cb = document.getElementById("cb");
    console.log(cb.checked);

    //为属性设值
    div1.id = "div2";
    div1["id"] = "div3";
    div1.setAttribute("id","div4");
    div1.setAttribute("xxx","ooo");

    //自定义属性
    console.log(div1.xxx);
    console.log(div1["xxx"]);
    console.log(div1.getAttribute("xxx"));
}