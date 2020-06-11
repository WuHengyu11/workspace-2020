package cn.wolfcode.luowowo.cloud.web.advice;

import cn.wolfcode.luowowo.cloud.common.CommonControllerAdvice;
import cn.wolfcode.luowowo.cloud.common.Result;
import cn.wolfcode.luowowo.cloud.web.msg.MemberCodeMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemberControllerAdvice extends CommonControllerAdvice {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception ex){
        return Result.error(MemberCodeMsg.DEFAULT_ERROR);
    }
}
