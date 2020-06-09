package cn.wolfcode._03_scope;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope/a")
public class AServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//网request作用域中添加共享数据
		req.setAttribute("name", "关羽");
		
		//网session作用域中添加共享数据
		req.getSession().setAttribute("name", "张飞");
		
		//网application作用域中添加共享数据
		req.getServletContext().setAttribute("name", "刘备");
		
		//请求转发,保证a和b在同一个请求中
		//req.getRequestDispatcher("/scope/b").forward(req, res);
		res.sendRedirect("/tes/scope/b");
	}
}
