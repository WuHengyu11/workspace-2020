package cn.wolfcode.spring;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wolfcode.spring.handler.TransactionInvocationHandler;
import cn.wolfcode.spring.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdkProxyTest {
	
	
	@Autowired
	private TransactionInvocationHandler transactionInvocationHandler;
	
	@Test
	public void testSave() throws Exception {
		
		// 下面这个方法创建代理类，并创建这个代理的对象返回, proxy 存的就是代理对象
		IEmployeeService proxy = (IEmployeeService)Proxy.newProxyInstance(
				this.getClass().getClassLoader(), // 类加载器
				transactionInvocationHandler.getTarget() // 获取真实对象
				.getClass().getInterfaces(), // 获取真实对象实现接口，到时生成代理对象就实现同样的接口 
				transactionInvocationHandler); // 生成代理对象做什么事情
		
		proxy.save("ls", "234");
	}

}
