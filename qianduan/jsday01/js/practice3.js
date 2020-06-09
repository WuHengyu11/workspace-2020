//定义一个函数
function Person(name,age){
    //添加属性
    this.name = name;
    this.age = age;

    //添加方法
    this.sleep = function(){
        console.log("I want to sleep!");
    }
}

//创建对象
var p = new Person("张飞",10);
console.log(p);

//访问对象中的成员
console.log(p.name);
console.log(p.age);

p.sleep();

//单独为某个对象添加成员
p.xxx = "000";
console.log(p);

var p2 = new Person("xxx",20);
console.log(p2);