package cn.wolfcode.web.interceptor;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.util.RequiredPermission;
import cn.wolfcode.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前用户
        //Employee username = (Employee)request.getSession().getAttribute("USERNAME_IN_SESSION");
        Employee username = UserContext.getCurrentUser();
        //判断当前用户是否为超级管理员,是则放行
        if (username.isAdmin()) {
            return true;
        }
        //获取当前要访问的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断该方法是否贴注解,没贴就放行
        RequiredPermission annotation = handlerMethod.getMethodAnnotation(RequiredPermission.class);
        if (annotation == null) {
            return true;
        }
        //获取该方法对应的权限表达式
        String expression = annotation.expression();
        //获取当前登录的用户已经拥有的权限
        List<String> permissions = UserContext.getPermissions();
        //判断该方法对应的权限表达式是否存在用户已经拥有的权限表达式中,如果有则放行
        if (permissions.contains(expression)){
            return true;
        }
        //不存在就放行
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        return false;
    }
}
