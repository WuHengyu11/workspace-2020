package cn.wolfcode.javaweb2.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.javaweb2.domain.Employee;
import cn.wolfcode.javaweb2.service.IEmployeeService;
import cn.wolfcode.javaweb2.service.impl.EmployeeServiceImpl;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//关联对象
	private IEmployeeService service = new EmployeeServiceImpl();
			
	/**
	 * 用来进行操作的请求分发
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置请求编码
		req.setCharacterEncoding("UTF-8");
		
		String cmd = req.getParameter("cmd");//获取请求参数
		
		if("delete".equals(cmd)){
			delete(req, res);
		}else if("saveOrUpdate".equals(cmd)){
			saveOrUpdate(req, res);
		}else if("input".equals(cmd)){
			input(req, res);
		}else{
			list(req, res);
		}
	}
	
	/**
	 * 查询数据
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//调用查询方法
		List<Employee> e = service.listAll();
		
		//将数据共享到list.jsp
		req.setAttribute("e", e);
		
		//请求转发到list.jsp
		req.getRequestDispatcher("WEB-INF/views/employee/list.jsp").forward(req, res);
		
		
	}
	
	/**
	 * 删除数据
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取id
		String id = req.getParameter("id");
		
		//调用删除方法
		service.delete(Long.valueOf(id));
		
		//重定向到lits.jsp
		res.sendRedirect("/emp/employee");
	}
	
	/**
	 * 保存或更新数据
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String age = req.getParameter("age");
		String salary = req.getParameter("salary");
		
		
		Employee e = new Employee();
		e.setName(name);
		e.setEmail(email);
		
		if(hasLength(age)){
			e.setAge(Integer.valueOf(age));
		}
		
		if(hasLength(salary)){
			BigDecimal bd=new BigDecimal(salary);   
			e.setSalary(bd);
		}
		
		//保存数据时没有id
		//更新数据时必须有id
		String id = req.getParameter("id");
		if (hasLength(id)) {
			e.setId(Long.valueOf(id));
			service.update(e);
		}else{
			//将数据保存到数据库中
			service.save(e);
		}
		
		res.sendRedirect("/emp/employee");
	}
	
	//判断字符串是否有长度
	private boolean hasLength(String str){
		return str != null && str.trim().length() > 0;
	}
	
	/**
	 * 进行页面调整请求
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void input(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//将页面上的数据共享回去
		String id = req.getParameter("id");
		if(hasLength(id)){
			Employee e = service.get(Long.valueOf(id));
			req.setAttribute("e", e);
		}
		req.getRequestDispatcher("WEB-INF/views/employee/input.jsp").forward(req, res);
	}

}
