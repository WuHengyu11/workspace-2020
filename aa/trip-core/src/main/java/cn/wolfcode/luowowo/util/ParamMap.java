package cn.wolfcode.luowowo.util;

import java.util.HashMap;

/**
 * 参数映射类
 * 用于包装参数
 */
public class ParamMap extends HashMap<String,Object>{
    //增加一个自己的put方法
    //链式方法
    //sb.append().append()...
    public ParamMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
