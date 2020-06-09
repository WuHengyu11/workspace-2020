package cn.wolfcode.web.interceptor;

import cn.wolfcode.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    private IPermissionService permissionServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //session获取登录用户
        HttpSession session = request.getSession();
        Object employee = session.getAttribute("EMPLOYEE_IN_SESSION");
        //如果不为空
        if(employee!= null){
            return true;
        }
        //重定向登录页面
        response.sendRedirect("/login.html");
        return false;
    }
}
