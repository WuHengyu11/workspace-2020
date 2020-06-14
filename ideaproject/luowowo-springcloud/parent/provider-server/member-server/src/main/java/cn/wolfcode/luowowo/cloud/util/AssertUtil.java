package cn.wolfcode.luowowo.cloud.util;

import cn.wolfcode.luowowo.cloud.common.BusinessException;
import cn.wolfcode.luowowo.cloud.common.CodeMsg;
import cn.wolfcode.luowowo.cloud.web.msg.MemberCodeMsg;
import org.springframework.util.StringUtils;

/**
 * 进行参数判断工具类
 */
public class AssertUtil {
    /**
     * 参数的空值判断
     * @param value
     * @param codeMsg
     */
    public static void hasLength(String value, CodeMsg codeMsg) {
        if (!StringUtils.hasLength(value)){
            throw new BusinessException(codeMsg);
        }
    }

    /**
     * 判断两个参数是否一致
     * @param value1
     * @param value2
     * @param codeMsg
     */
    public static void isEquals(String value1, String value2, CodeMsg codeMsg) {
        if (value1 == null || value2 == null){
            throw new BusinessException(MemberCodeMsg.PARAM_NULL_ERROR);
        }
        if (!value1.equals(value2)){
            throw new BusinessException(codeMsg);
        }
    }
}
