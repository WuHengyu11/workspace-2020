package cn.wolfcode.luowowo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录检查注解:
 * 1: 如果方法贴有这个标签, 表示该方法必须登录之后才可以访问
 * 2: 如果没有贴, 表示不需要进行任何检查吗直接访问
 */
@Target({ElementType.METHOD})  //贴到方法上
@Retention(RetentionPolicy.RUNTIME)  //标签的有效期, 运行时期
public @interface RequireLogin {
}
