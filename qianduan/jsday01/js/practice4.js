//使用简单方法遍历数组
var arr = ["A","B","C","D"];
for (var i = 0; i < arr.length; i++) {
   console.log(arr[i]);
}

//使用foreach遍历数组
var arr = ["1","2","3","4"];
arr.forEach(element => {
    console.log(element);
});

//使用map遍历数组
var arr = ["A1","B1","C1","D1"];
var newArr = arr.map(function(item,index,array){
      return item+index;
     });

//使用forin遍历数组

//如果是遍历数组,i对应的是数组的索引
var arr = ["A","B","C","D"];
for (var i in arr) {
    console.log(i);
    console.log(arr[i]);
}

//如果是遍历对象,name对应的是属性名
var obj = new Object();
for (var name in obj) {
    console.log(name);
    console.log(obj[name]);
}
