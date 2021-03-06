package cn.wolfcode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	//提供方法处理请求
	@RequestMapping("/hello")
	public ModelAndView hello(){
		//往作用域或模型中存入数据
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "Hello SPring mvc");
		//找视图
		mv.setViewName("/WEB-INF/views/welcome.jsp");
		return mv;
	}
}
