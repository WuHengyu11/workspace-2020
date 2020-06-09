package cn.wolfcode.spring._01_hello;

public class Persion {
	private String  name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void doWork(){
		System.out.println(name + "疯狂工作");
	}
}
