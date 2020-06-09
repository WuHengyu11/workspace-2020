package cn.wolfcode.luowowo.advice;

import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通过动态代理方式对controller中请求方法异常机芯统一处理
 *
 * @ControllerAdvice: controller类的功能增强标签
 *
 * 在请求方法处理之前,可以对请求方法进行功能增强 : 参数改造-date类型数据格式化
 *
 * 在请求方法处理之后,可以对请求方法进行功能增强 : 请求方法统一异常处理
 *
 *
 */
@ControllerAdvice
public class CommonExceptionHandler {

    //下面方法, 写法给请求映射规则一样

    //明确指定标记的方法可以处理哪种类型的异常
    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public Object displayExp(LogicException e, Model model){
        e.printStackTrace();
        return JsonResult.error(JsonResult.CODE_ERROR, e.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object  runTimeExp(Exception e){
        e.printStackTrace();
        return JsonResult.defaultError();
    }
}
