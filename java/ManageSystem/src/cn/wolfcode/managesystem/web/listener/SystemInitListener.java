package cn.wolfcode.managesystem.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.wolfcode.managesystem.domain.User;
import cn.wolfcode.managesystem.service.IUserService;
import cn.wolfcode.managesystem.service.impl.UserServiceImpl;

public class SystemInitListener implements ServletContextListener {
	//关联对象
	private IUserService service = new UserServiceImpl();
	//正常关闭服务器时执行
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	//启动服务器时执行
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		User u = service.getByUserName("admin");
		if(u == null){
			u = new User();
			u.setUsername("admin");
			u.setPassword("123456");
			service.save(u);
		}
	}

}
