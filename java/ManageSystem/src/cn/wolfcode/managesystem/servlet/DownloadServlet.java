package cn.wolfcode.managesystem.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//检查用户权限(是否登录?是否是会员?是否月足够)
		
		//如果条件满足,在执行下载操作
		//找资源
		String filename = req.getParameter("filename");
		
		
		String realPath = req.getServletContext().getRealPath("/download");
		File f = new File(realPath,filename);
		
		//将找到的资源响应给浏览
		//处理中午乱码问题
		String header = req.getHeader("User-Agent");
		if (header.contains("MSIE")) {
			//IE
			filename = URLEncoder.encode(filename,"UTF-8");
		}else{
			//非IE
			filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		//将文件名告诉浏览器
		res.setHeader("Content-Disposition", "attachment;filename=" + filename);
		//将文件写道输出流中
		Files.copy(Paths.get(f.getAbsolutePath()), res.getOutputStream());
	}
}
