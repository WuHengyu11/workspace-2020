package cn.wolfcode._02_redirect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect/a")
public class AServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		System.out.println("a....before");
		
		//URL重定向
		res.sendRedirect("/tes/redirect/b");
		
		System.out.println("a....after");
		res.getWriter().println("a...response");
	}
}
