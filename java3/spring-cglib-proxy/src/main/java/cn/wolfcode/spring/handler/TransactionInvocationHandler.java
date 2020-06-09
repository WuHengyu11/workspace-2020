package cn.wolfcode.spring.handler;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;

import cn.wolfcode.spring.tx.MyTxManager;

public class TransactionInvocationHandler implements InvocationHandler {

	// 存在真实对象的引用
	private Object target;
	public void setTarget(Object target) {
		this.target = target;
	}
	public Object getTarget() {
		return target;
	}
	
	// 存在事务模拟对象的引用
	private MyTxManager tx;
	public void setTx(MyTxManager tx) {
		this.tx = tx;
	}
	
	
	// 通过这个方法告诉JDK 给生成类的对象做什么事情
	// 这个方法给代理对象调用的
	@Override
	// proxy 代理对象   调用代理的对象方法   调用代理对象实参
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/*System.out.println("到此一游");
		return null;*/
		Object retValue = null;
		try {
			tx.begin();
			// 因为 target 是真实对象
			retValue = method.invoke(target, args); // 调用真实对象的方式
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		}
		return retValue;
	}

}
