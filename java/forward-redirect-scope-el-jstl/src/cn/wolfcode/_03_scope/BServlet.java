package cn.wolfcode._03_scope;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope/b")
public class BServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String str = (String) req.getAttribute("name");
		System.out.println(str);
		
		String str1 = (String) req.getSession().getAttribute("name");
		System.out.println(str1);
		
		String str2 = (String) req.getServletContext().getAttribute("name");
		System.out.println(str2);
	}
}
