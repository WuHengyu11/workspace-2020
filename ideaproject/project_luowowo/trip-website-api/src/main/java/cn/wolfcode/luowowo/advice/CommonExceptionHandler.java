package cn.wolfcode.luowowo.advice;

import cn.wolfcode.luowowo.exception.LogicException;
import cn.wolfcode.luowowo.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public Object displayExp(LogicException e){
        e.printStackTrace();
        return JsonResult.error(JsonResult.CODE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object runTimeExp(Exception e){
        e.printStackTrace();
        return JsonResult.defaultError();
    }
}
