package cn.wolfcode.util;

import cn.wolfcode.domain.Employee;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class UserContext {
    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        //Spring mvc提供的获取请求相关的属性工具类(可以很方便的在任意地方获取request,response,session)
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }
    /**
     * 设置当前登录用户到session中
     * @param employee
     */
    public static void setCurrentUser(Employee employee){
        getSession().setAttribute("USERNAME_IN_SESSION",employee);
    }

    /**
     * 从session中获取当前登录账户
     * @return
     */
    public static Employee getCurrentUser(){
        return (Employee) getSession().getAttribute("USERNAME_IN_SESSION");
    }

    //设置当前登录用户拥有的权限到session中
    public static void setPermissions(List<String> permissions){
        getSession().setAttribute("EXPRESSION_IN_SESSION",permissions);
    }
    //设置当前登录用户拥有的权限到session中
    public static List<String> getPermissions(){
        return (List<String>) getSession().getAttribute("EXPRESSION_IN_SESSION");
    }

}
