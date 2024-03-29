package cn.wolfcode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController {
	public ModelAndView sayHello(){
		//创建视图模型对象
		ModelAndView mv = new ModelAndView();
		//设置视图名称
		mv.setViewName("/WEB-INF/views/welcome.jsp");
		//往模型存放数据
		mv.addObject("message", "Hello Spring MVC");
		return mv;
	}
}
