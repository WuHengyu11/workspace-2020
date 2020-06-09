package cn.wolfcode.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wolfcode.spring.handler.TransactionInvocationHandler;
import cn.wolfcode.spring.service.impl.EmployeeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CglibProxyTest {
	
	
	@Autowired
	private TransactionInvocationHandler transactionInvocationHandler;
	
	@Test
	public void testSave() throws Exception {
		
		Enhancer enhancer = new Enhancer();
		// 设置代理类父类, 代理类是继承真实类
		enhancer.setSuperclass(transactionInvocationHandler.getTarget().getClass());
		// 后面创建出来代理对象做什么事情
		enhancer.setCallback(transactionInvocationHandler);
		// 创建代理类及其对象，强转
		EmployeeServiceImpl proxy = (EmployeeServiceImpl)enhancer.create();
		proxy.save("ls", "234");
	}

}
