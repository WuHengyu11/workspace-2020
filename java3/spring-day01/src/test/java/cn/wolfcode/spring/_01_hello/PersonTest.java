package cn.wolfcode.spring._01_hello;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
	@Test
	public void testOld() throws Exception {
		Persion persion = new Persion();
		persion.setName("小明");
		persion.doWork();
	}
	@Test
	public void testNew() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:hello.xml");
		Persion p = (Persion) ctx.getBean("person");
		p.doWork();
	}
}
