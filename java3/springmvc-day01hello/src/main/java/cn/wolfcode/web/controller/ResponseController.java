package cn.wolfcode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseController {
	@RequestMapping("/f")
	public String forward() {
		return "forward:/WEB-INF/views/welcome.jsp"; 
	}
	
	// 提供方法处理请求
	@RequestMapping("/r") 
	public String redirect(Model model) {
		return "redirect:/static/static.html"; 
	}
	
	
	
	// 提供方法处理请求
	@RequestMapping("/resp1") 
	public ModelAndView resp1() {
		// 通过创建这个类对象，到Spring MVC 我们我找什么视图文件， 往作用域或者说往模型中存入什么数据
		ModelAndView mv = new ModelAndView();
		// 往作用域或者模型中存入数据
		mv.addObject("msg", "方法返回类型是ModelAndView");
	   // 找视图
		mv.setViewName("resp"); // 返回视图名 /WEB-INF/views/resp.jsp
		return mv;
	}
	
	// 提供方法处理请求
	@RequestMapping("/resp2")
	public String resp2(Model model) {
		model.addAttribute("msg", "方法返回类型是String"); // 往作用域或者模型中存入数据
		return "resp"; // 返回视图名 /WEB-INF/views/resp.jsp
	}
}
