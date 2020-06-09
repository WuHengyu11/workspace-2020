package cn.wolfcode.luowowo.exception;

/**
 * 业务逻辑的异常,需要给用户看的异常
 */
public class LogicException extends RuntimeException{
    public LogicException(String msg){
        super(msg);
    }
}
