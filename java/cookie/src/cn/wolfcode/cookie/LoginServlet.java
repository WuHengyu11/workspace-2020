package cn.wolfcode.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		
		//将获取到的账号使用cookie来实现共享
		//办卡
		Cookie c = new Cookie("username", username);
		//交给用户
		res.addCookie(c);
		String password = req.getParameter("password");
		if("admin".equals(username) && "1".equals(password)){
			req.setAttribute("username", username);
			req.getRequestDispatcher("/index.jsp").forward(req, res);
		}
	}
	
}
