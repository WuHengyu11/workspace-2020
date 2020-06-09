package cn.wolfcode.luowowo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录检查注解:
 * 1:如果请求方法中贴有这个注解,表示该方法必须登录后才可以访问
 * 2:如果请求方法没贴这个注解,表示该请求方法不做任何限制
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireLogin {
}
