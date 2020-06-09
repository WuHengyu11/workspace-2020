function fun1(){
    var sapn1 = document.getElementById("span1");
    var div1 = document.getElementById("div1");
    div1.appendChild(sapn1);
}

function fun2(){
    var sapn2 = document.getElementById("span2");
    var div2 = document.getElementById("div2");
    div2.appendChild(sapn2);
}

function fun3(){
    var span = document.createElement("span");
    span.innerText = "span";
    var div3 = document.getElementById("div3");
    div3.appendChild(span);
}

function fun4(){
    var character = document.getElementById("character");
    var option = document.createElement("option");
    option.innerText = "诸葛亮";
    var item2 = document.getElementById("item2");
    character.insertBefore(option,item2);
}

function fun5(){
    var character = document.getElementById("character");
    var option = document.createElement("option");
    option.innerText = "魏延";
    var item2 = document.getElementById("item2");
    character.replaceChild(option,item2);
}

function fun6(){
    var character = document.getElementById("character");
    var item3 = document.getElementById("item3");
    character.removeChild(item3);
}
