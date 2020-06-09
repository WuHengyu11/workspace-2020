package cn.wolfcode.spring.service.impl;

import cn.wolfcode.spring.service.IEmployeeService;
import cn.wolfcode.spring.tx.MyTxManager;

// 代理类 代理对象 中介 二手房东
public class EmployeeServiceProxy implements IEmployeeService{
	
	// 存在房东引用， 存在房东联系方式
	private IEmployeeService target;
	
	public void setTarget(IEmployeeService target) {
		this.target = target;
	}
	
	private MyTxManager tx;
	public void setTx(MyTxManager tx) {
		this.tx = tx;
	}

	@Override
	public void save(String username, String password) {
		try {
			tx.begin();
			target.save(username, password); // 调用真实对象的方法
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		}
	}

}
