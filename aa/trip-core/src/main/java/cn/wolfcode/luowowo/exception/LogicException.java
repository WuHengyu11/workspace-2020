package cn.wolfcode.luowowo.exception;

/**
 * 自定义异常, 用于区分给用户看异常与系统异常
 */
public class LogicException extends RuntimeException {

    public LogicException(String message) {
        super(message);
    }
}
