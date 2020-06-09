package cn.keeppassword.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/keeppassword")
public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		Cookie nameCookie = null;
		Cookie passwordCookie = null;
		Cookie statusCookie = null;
		System.out.println(username + password + remember);
		if(username != null && password != null && remember != null){
			nameCookie = new Cookie("username", username);
			passwordCookie = new Cookie("password", password);
			statusCookie = new Cookie("remember", remember);
			nameCookie.setMaxAge(60*60*24);
			passwordCookie.setMaxAge(60*60*24);
			statusCookie.setMaxAge(60*60*24);
			res.addCookie(nameCookie);
			res.addCookie(passwordCookie);
			res.addCookie(statusCookie);
			//req.setAttribute("remember", remember);
			req.getRequestDispatcher("/longin.jsp").forward(req, res);
		}else{
			nameCookie = new Cookie("username", username);
			passwordCookie = new Cookie("password", password);
			statusCookie = new Cookie("remember", remember);
			nameCookie.setMaxAge(0);
			passwordCookie.setMaxAge(0);
			statusCookie.setMaxAge(0);
			res.addCookie(nameCookie);
			res.addCookie(passwordCookie);
			res.addCookie(statusCookie);
			req.getRequestDispatcher("/longin.jsp").forward(req, res);
		}
	
	}
}
