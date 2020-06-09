package cn.wolfcode.javaweb.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.javaweb.domain.Product;
import cn.wolfcode.javaweb.service.IProductService;
import cn.wolfcode.javaweb.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
		//关联对象
		private IProductService service = new ProductServiceImpl();
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//设置编码格式
			req.setCharacterEncoding("UTF-8");
			String cmd = req.getParameter("cmd");
			System.out.println(cmd);
			if("delete".equals(cmd)){
				delete(req, res);
			}else if("input".equals(cmd)){
				input(req, res);
			}else if("saveOrUpdate".equals(cmd)){
				saveOrUpdate(req, res);
			}else{
				list(req,res);
			}
		}
		
		//用来进行查询操作
		protected void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//调用查询方法
			List<Product> productList = service.listAll();
			System.out.println(productList);
			
			//将集合共享给list.jsp
			req.setAttribute("productList", productList);
			req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, res);
		}
		
		//处理删除请求
		protected void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String id = req.getParameter("id");
			service.delete(Long.valueOf(id));
			res.sendRedirect("/pro/product");
		}
		
		//用来进行插入和更新数据
		protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			String productName = req.getParameter("productName");
			String salePrice = req.getParameter("salePrice");
			String supplier = req.getParameter("supplier");
			String brand = req.getParameter("brand");
			String cutoff = req.getParameter("cutoff");
			String costPrice = req.getParameter("costPrice");
			String dir_id = req.getParameter("dir_id");
			
			Product p = new Product();
			p.setProductName(productName);
			p.setSupplier(supplier);
			p.setBrand(brand);
			
			if(hasLength(salePrice)){
				p.setSalePrice(Double.valueOf(salePrice));
			}
			
			if(hasLength(cutoff)){
				p.setSalePrice(Double.valueOf(cutoff));
			}
			
			if(hasLength(costPrice)){
				p.setSalePrice(Double.valueOf(costPrice));
			}
			
			if(hasLength(dir_id)){
				p.setSalePrice(Double.valueOf(dir_id));
			}
			
			//保存数据时没有id
			//更新数据时必须有id
			String id = req.getParameter("id");
			if (hasLength(id)) {
				p.setId(Long.valueOf(id));
				service.update(p);
			}else{
				//将数据保存到数据库中
				service.save(p);
			}
			
			res.sendRedirect("/pro/product");
		}
		//判断字符串是否有长度
		private boolean hasLength(String str){
			return str != null && str.trim().length() > 0;
		}
		//用来处理录入页面跳转请求
		protected void input(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			//将页面上的数据共享回去
			String id = req.getParameter("id");
			if(hasLength(id)){
				Product p = service.get(Long.valueOf(id));
				req.setAttribute("p", p);
			}
			req.getRequestDispatcher("/WEB-INF/views/product/input.jsp").forward(req, res);
		}
}
