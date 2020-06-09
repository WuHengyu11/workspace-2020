package cn.wolfcode.managesystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wolfcode.managesystem.domain.User;
import cn.wolfcode.managesystem.service.IUserService;
import cn.wolfcode.managesystem.service.impl.UserServiceImpl;
import cn.wolfcode.managesystem.util.StringIsEmpty;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//关联对象
	private IUserService service = new UserServiceImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//验证码校验
		//获取到用户的验证码
		String randomCode = req.getParameter("randomCode");
		//获取session的验证码
		String SessionRandomCode = (String) req.getSession().getAttribute("RANDOMCODE_IN_SESSION");
		if(StringIsEmpty.hasLength(randomCode) && StringIsEmpty.hasLength(SessionRandomCode)){
			if(!randomCode.equals(SessionRandomCode)){
				req.setAttribute("errorMsg", "验证码错误");
				req.getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
		}else{
			
		}
		
		
		//接收用户传递的账号和密码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Cookie usernameCookie;
		Cookie passwordCookie;
		Cookie rememberMeCookie;
		//接收用户是否保存登录信息
		String rememberMe = req.getParameter("rememberMe");
		if(StringIsEmpty.hasLength(rememberMe)){
			usernameCookie = new Cookie("username", username);
			passwordCookie = new Cookie("password", password);
			rememberMeCookie = new Cookie("rememberMe", rememberMe);
			usernameCookie.setMaxAge(60*60*24*7);
			passwordCookie.setMaxAge(60*60*24*7);
			rememberMeCookie.setMaxAge(60*60*24*7);
			res.addCookie(usernameCookie);
			res.addCookie(passwordCookie);
			res.addCookie(rememberMeCookie);
		}else{
			usernameCookie = new Cookie("username", username);
			passwordCookie = new Cookie("password", password);
			rememberMeCookie = new Cookie("rememberMe", rememberMe);
			usernameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
			rememberMeCookie.setMaxAge(0);
			res.addCookie(usernameCookie);
			res.addCookie(passwordCookie);
			res.addCookie(rememberMeCookie);
		}
		//传递给service进行查询
		User login = service.login(username, password);
	
		if(login != null){
			HttpSession session = req.getSession();
			session.setAttribute("USER_IN_SESSION", login);
			//跳转
			res.sendRedirect("/department");
		}else{
			req.setAttribute("errorMsg", "您的账号或者密码错误");
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}
}
