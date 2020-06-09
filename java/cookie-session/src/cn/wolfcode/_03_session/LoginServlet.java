package cn.wolfcode._03_session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/session")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		
		//用session将数据共享
		//获取session对象i
		HttpSession session = req.getSession();
		//添加共享数据
		session.setAttribute("username", username);
		
		String password = req.getParameter("password");
		if("admin".equals(username) && "1".equals(password)){
			req.setAttribute("username", username);
			req.getRequestDispatcher("/index.jsp").forward(req, res);
		}
	}
	
}
