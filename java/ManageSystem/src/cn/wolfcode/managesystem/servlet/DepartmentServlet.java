package cn.wolfcode.managesystem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.managesystem.domain.Department;
import cn.wolfcode.managesystem.query.PageResult;
import cn.wolfcode.managesystem.query.QueryObject;
import cn.wolfcode.managesystem.service.IDepartmentService;
import cn.wolfcode.managesystem.service.impl.DepartmentServiceImpl;
import cn.wolfcode.managesystem.util.StringIsEmpty;


@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//关联对象
	private IDepartmentService service = new DepartmentServiceImpl();
	
	/**
	 * 进行请求分发
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//设置请求编码
		req.setCharacterEncoding("UTF-8");
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
		req.getRequestDispatcher("WEB-INF/views/department/list.jsp").forward(req, res);
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
		res.sendRedirect("/department");
	}
	
	/**
	 * 保存或更新数据
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String dep_name = req.getParameter("dep_name");
		String dep_id = req.getParameter("dep_id");
				
		Department department = new Department();
		department.setDep_name(dep_name);
		department.setDep_id(dep_id);
		
		//保存数据时没有id
		//更新数据时必须有id
		String id = req.getParameter("id");
		if (StringIsEmpty.hasLength(id)) {
			department.setId(Long.valueOf(id));
			service.update(department);
		}else{
			//将数据保存到数据库中
			service.save(department);
		}
		
		res.sendRedirect("/department");
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
			Department d = service.get(Long.valueOf(id));
			req.setAttribute("d", d);
		}
		req.getRequestDispatcher("WEB-INF/views/department/input.jsp").forward(req, res);
	}
}
