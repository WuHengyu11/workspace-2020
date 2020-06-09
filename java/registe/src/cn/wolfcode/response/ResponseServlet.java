package cn.wolfcode.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setCharacterEncoding("UTF-8");
		//设置响应数据的格式
		res.setContentType("text/htm;charset=UTF-8");
		
		//响应数据给浏览器
		PrintWriter out = res.getWriter();
		out.print("注册成功");
	}
}
