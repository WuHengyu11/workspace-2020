package cn.wolfcode.luowowo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于区分自定义参数解析器与springmvc默认参数解析器
 */
@Target({ElementType.PARAMETER})  //贴到方法上
@Retention(RetentionPolicy.RUNTIME)  //标签的有效期, 运行时期
public @interface UserParam {
}
