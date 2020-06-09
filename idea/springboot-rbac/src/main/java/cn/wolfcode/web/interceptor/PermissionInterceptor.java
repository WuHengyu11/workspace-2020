package cn.wolfcode.web.interceptor;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.util.RequiredPermission;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //获取当前登录用户
        Employee employee = (Employee) request.getSession().getAttribute("EMPLOYEE_IN_SESSION");
        //判断是否是超级管理员
        boolean admin = employee.isAdmin();
        if(admin){
            return true;
        }
        //获取当前要访问的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断是否有注解
        RequiredPermission methodAnnotation = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        if(methodAnnotation == null){
            return true;
        }
        //获取方法对应的权限表达式
        String expression = methodAnnotation.expression();
        //获取当前登陆用户方法自身拥有的权限表达式
        List permissions = (List) request.getSession().getAttribute("PERMISSION_IN_SESSION");
        //判断方法对应的权限表达式是否存在已经拥有的权限表达式列表中
        System.out.println(permissions);
        if(permissions.contains(expression)){
            return true;
        }
        //不存在就跳转错误页面
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }
}
