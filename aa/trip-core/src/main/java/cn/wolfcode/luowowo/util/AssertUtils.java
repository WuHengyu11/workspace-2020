package cn.wolfcode.luowowo.util;


import cn.wolfcode.luowowo.exception.LogicException;
import org.springframework.util.StringUtils;

/**
 * 参数断言判断
 */
public class AssertUtils {


    /**
     * 判断指定value参数是否有值, 如果没有抛出异常, 信息: msg
     * @param v
     * @param msg
     */
    public static void hasLength(String v, String msg) {

        if(!StringUtils.hasLength(v)){
            throw new LogicException(msg);
        }

    }

    /**
     * 判断传入的2个参数是否相等
     * @param v1
     * @param v2
     * @param msg
     */
    public static void isEquals(String v1 , String v2, String msg) {

        if(v1 == null || v2 == null){
            throw new RuntimeException("传入参数不能为null");
        }

        if(!v1.equals(v2)){
            throw new LogicException(msg);
        }
    }
}
