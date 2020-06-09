package cn.wolfcode.shiro;

import cn.wolfcode.qo.JsonResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component("ajaxFormAuthenticationFilter")
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter{
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //使用response输出json的数据给前端
        JsonResult jsonResult = new JsonResult();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(jsonResult));
        return false;//不放行
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String msg = "";
        if (e instanceof UnknownAccountException){
            msg="账号不存在";
        }else if (e instanceof IncorrectCredentialsException){
            msg="密码错误";
        }else if (e instanceof DisabledAccountException) {
            msg = "账号已被冻结,请联系管理员";
        }
        JsonResult jsonResult = new JsonResult(false,msg);
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print(JSON.toJSONString(jsonResult));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;//不放行
    }
}
