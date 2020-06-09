package cn.wolfcode.managesystem.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.managesystem.domain.Employee;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.service.IEmployeeService;
import cn.wolfcode.managesystem.service.impl.EmployeeServiceImpl;
import cn.wolfcode.managesystem.util.FileUploadUtil;
import cn.wolfcode.managesystem.util.LogicException;
import cn.wolfcode.managesystem.util.StringIsEmpty;


@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//关联对象
	private IEmployeeService service = new EmployeeServiceImpl();
	
	/**
	 * 进行请求分发
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String cmd = req.getParameter("cmd");
		
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
		QueryObject qo = new QueryObject();
		
		//获取页码
		String currentPage = req.getParameter("currentPage");
		//进行合理值判断
		if(currentPage != null && !"".equals(currentPage.trim())){
			qo.setCurrentPage(Integer.parseInt(currentPage));
		}
		
		PageResult pageResult = service.query(qo);
		//共享查询到的数据
		req.setAttribute("pageResult", pageResult);
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
		res.sendRedirect("/employee");
	}
	
	/**
	 * 保存或更新数据
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Map<String, String> map = null;
		try {
			map = FileUploadUtil.upload(req);
		} catch (LogicException e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", e.getMessage());
			req.getRequestDispatcher("WEB-INF/views/employee/input.jsp").forward(req, res);
			return;
		}

		String name = map.get("name");
		String email = map.get("email");
		String age = map.get("age");
		String salary = map.get("salary");
		String img = map.get("img");
		
		Employee e = new Employee();
		e.setName(name);
		e.setEmail(email);
		e.setImg_path(img);
		if(StringIsEmpty.hasLength(age)){
			e.setAge(Integer.valueOf(age));
		}
		
		if(StringIsEmpty.hasLength(salary)){
			BigDecimal bd=new BigDecimal(salary);   
			e.setSalary(bd);
		}
		
		//保存数据时没有id
		//更新数据时必须有id
		String id = req.getParameter("id");
		if (StringIsEmpty.hasLength(id)) {
			e.setId(Long.valueOf(id));
			service.update(e);
		}else{
			//将数据保存到数据库中
			service.save(e);
		}
		
		res.sendRedirect("/employee");
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
		if(StringIsEmpty.hasLength(id)){
			Employee e = service.get(Long.valueOf(id));
			req.setAttribute("e", e);
		}
		req.getRequestDispatcher("WEB-INF/views/employee/input.jsp").forward(req, res);
	}
}
