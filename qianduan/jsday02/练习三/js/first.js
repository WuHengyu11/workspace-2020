function add(){
    //创建tr,四个td元素
    var tr = document.createElement("tr");
    //创建td
    var usernameTd = document.createElement("td");
    var emailTd = document.createElement("td");
    var telTd = document.createElement("td");
    var deleteTd = document.createElement("td");

    //获取文本框中的数据,使用input元素的value属性
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value; 
    var tel = document.getElementById("tel").value;

    //拼接元素
    usernameTd.innerHTML = username;
    emailTd.innerHTML = email; 
    telTd.innerHTML = tel; 
    deleteTd.innerHTML="<a href='#' onclick='deleteUser(this)'>删除</a>";

    //将td追加到tr中
    tr.appendChild(usernameTd);
    tr.appendChild(emailTd);
    tr.appendChild(telTd);
    tr.appendChild(deleteTd);

    //将tr追加到tbody中
    document.getElementById("userTbody").appendChild(tr);
}

function deleteUser(aEle){
     //删除指定元素
     var tr = aEle.parentNode.parentNode;
     tr.parentNode.removeChild(tr);
    }

    window.onload = () => {
        //为删除所有按钮绑定事件
        document.getElementById("btn_removeAll").onclick = () =>{
            document.getElementById("userTbody").innerHTML="";
        }
    }