package cn.wolfcode.luowowo.cloud.web.advice;

import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.msg.MsgCodeMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MsgControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        return Result.error(MsgCodeMsg.DEFAULT_ERROR);
    }
}
