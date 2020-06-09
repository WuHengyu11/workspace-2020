package cn.wolfcode._04_bean;

public class SomeBean {
    public SomeBean(){
        System.out.println("构建了.....");
    }
    public void destroy(){
        System.out.println("销毁了.....");
    }

    public void init(){
        System.out.println("初始化.....");
    }
}
