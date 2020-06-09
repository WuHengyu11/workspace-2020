package cn.wolfcode.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ControllerAdvice controller功能增强标签
 * 在请求进入请求方法处理之前,对请求方法做功能增强
 *      对请求参数做处理,date日期类型格式化统一处理
 * 在请求进入请求方法处理之后,对请求方法做功能增强
 *      对请求处理过程中,出现问题进行处理,统一异常处理
 *
 */
@ControllerAdvice
public class GlobalErrorAdvice {
    //异常处理方法,跟请求方法一样
    //明确处理那个类型的日常
    @ExceptionHandler(RuntimeException.class)
    public String handleExp(Model model, HttpServletRequest request, HttpSession session,Exception e){
        model.addAttribute("error",e.getMessage());
        return "freemaker/err";
    }

}
