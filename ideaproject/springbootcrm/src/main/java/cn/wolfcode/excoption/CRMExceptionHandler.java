package cn.wolfcode.excoption;

import cn.wolfcode.qo.JsonResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对控制器进行增强
 */
public class CRMExceptionHandler {

    /**
     * 捕获运行过程中出现的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handleException(Exception ex, HandlerMethod handlerMethod, HttpServletResponse response) throws IOException {
        ex.printStackTrace();

        if(handlerMethod.hasMethodAnnotation(ResponseBody.class)){
            // 如果是ajax 对象方法返回json数据
            JsonResult json = new JsonResult(false,"系统异常,请联系管理员");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(json));
            return null;
        }else{
            // 返回错误数据
            return "common/error";

        }

    }

    /**
     * 指定要捕获的异常(shiro没有权限的异常)
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String handleShiroException(Exception ex,HandlerMethod handlerMethod,HttpServletResponse response) throws IOException {
        ex.printStackTrace();

        if (handlerMethod.hasMethodAnnotation(ResponseBody.class)) {
            // 如果是ajax 对象方法返回json数据
            JsonResult json = new JsonResult(false, "您没有权限进行操作");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(json));
            return null;
        } else {
            // 返回错误数据
            return "common/nopermission";

        }
    }

}
