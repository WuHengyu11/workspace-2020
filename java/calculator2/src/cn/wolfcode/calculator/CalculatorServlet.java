package cn.wolfcode.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//设置请求编码格式
		req.setCharacterEncoding("UTF-8");
		//设置响应数据的格式
		res.setContentType("text/html;charset=UTF-8");
		
		String parameter1 = req.getParameter("parameter1");
		String parameter2 = req.getParameter("parameter2");
		String jisuan = req.getParameter("jisuan");
		
		Integer canshu1 = null;
		Integer canshu2 = null;
		Integer result = null;
		
		PrintWriter out = res.getWriter();
		
		//讲string类型转为Integer类型
		if(parameter1!=null&&parameter2!=null){
			canshu1 = Integer.valueOf(parameter1);
			canshu2 = Integer.valueOf(parameter2);
		}
		
		if("jia".equals(jisuan)){
			result = canshu1+ canshu2;
		}else if("jian".equals(jisuan)){
			result = canshu1- canshu2;
		}else if("chen".equals(jisuan)){
			result = canshu1*canshu2;
		}else{
			result = canshu1/canshu2;
		}
		
		StringBuilder sr = new StringBuilder("<input type='text' name='parameter1' ").append("value=").append("'").append(parameter1).append("'").append("/>");
		sr.append("&nbsp");
		sr.append("<select name='jisuan'>"
				+"<option value='jia' selected='selecte'>+</option>"
				+"<option value='jian'>-</option>"
				+"<option value='chen'>*</option>"
				+"<option value='chu'>/</option>"
				+"</select>"
				);
		sr.append("&nbsp");
		sr.append("<input type='text' name='parameter2' ").append("value=").append("'").append(parameter2).append("'").append("/>");
		sr.append("&nbsp");
		sr.append("<input type='submit' value='='>");
		sr.append("&nbsp");
		sr.append("<input type='text' name='result' ").append("value=").append("'").append(result).append("'").append("/>");
		out.print(sr.toString());
//System.out.println(result);
//System.out.println(parameter1);
//System.out.println(parameter2);
//System.out.println(canshu1);
//System.out.println(canshu2);
//System.out.println(jisuan);
	}

}
