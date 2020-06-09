package cn.wolfcode._01_forward;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward/a")
public class AServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		System.out.println("a....before");
		
		//请求转发(访问下一个组件)
		req.getRequestDispatcher("/forward/b").forward(req, res);
		System.out.println("a....after");
		res.getWriter().println("a...response");
	}
}
