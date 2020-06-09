package cn.wolfcode.spring.tx;

// 事务模拟类
public class MyTxManager {
	public void begin() {
		System.out.println("开启事务");
	}
	public void commit() {
		System.out.println("提交事务");
	}
	public void rollback() {
		System.out.println("回滚事务");
	}

}
