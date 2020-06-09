package cn.wolfcode.managesystem.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.wolfcode.managesystem.util.StringIsEmpty;

public class CharacterEncodingFilter implements Filter{
	private String encoding;
	private Boolean force;
	@Override
	public void destroy() {
		System.out.println("摧毁");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//设置请求编码
		//如果encoding有值,并且force值为true(强制使用)
		if(StringIsEmpty.hasLength(encoding) && (force ||	StringIsEmpty.hasLength(encoding) && req.getCharacterEncoding() == null)){
			req.setCharacterEncoding(encoding);
		}
		//放行
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		force = Boolean.valueOf(config.getInitParameter(config.getInitParameter("force")));
	}

}
