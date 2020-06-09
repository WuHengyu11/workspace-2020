package cn.wolfcode.luowowo.util;

import cn.wolfcode.luowowo.exception.LogicException;
import org.springframework.util.StringUtils;

/**
 * 进行参数判断工具类
 */
public class AssertUtil {
    /**
     * 参数的空值判断
     * @param value
     * @param msg
     */
    public static void hasLength(String value, String msg) {
        if (!StringUtils.hasLength(value)){
            throw new LogicException(msg);
        }
    }

    /**
     * 判断两个参数是否一致
     * @param value1
     * @param value2
     * @param msg
     */
    public static void isEquals(String value1, String value2, String msg) {
        if (value1 == null || value2 == null){
            throw new LogicException("参数不能为空!");
        }
        if (!value1.equals(value2)){
            throw new LogicException(msg);
        }
    }
}
