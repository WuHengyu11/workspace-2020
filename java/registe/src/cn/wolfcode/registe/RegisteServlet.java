package cn.wolfcode.registe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置Tomcat对请求参数的解码格式
		req.setCharacterEncoding("UTF-8");
		//设置响应数据的格式
		res.setContentType("text/htm;charset=UTF-8");
		
		//响应数据给浏览器
		PrintWriter out = res.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		out.print("注册成功");
	}

}
