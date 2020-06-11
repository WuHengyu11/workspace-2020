package cn.wolfcode.luowowo.cloud.web.advice;

import cn.wolfcode.luowowo.cloud.common.CommonControllerAdvice;
import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.msg.WebsiteCodeMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebsiteControllerAdvice extends CommonControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception ex){
        ex.printStackTrace();
        return Result.error(WebsiteCodeMsg.DEFAULT_ERROR);
    }
}
