package cn.wolfcode._02_ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration spring容器配置类标签
 * 如果类贴这个标签,表示该类为容器配置类
 * 等价于:applicationContext.xml
 */

@Configuration
public class AppConfig {

    //等价于:<bean id="SomeBean" class="cn.wolfcode._01_xml.SomeBean"></bean>
   //实例方法
    @Bean//spring的实例方法,贴有这个标签称之为示例方法
   //该方法返回的对象交给spring容器管理
    public SomeBean someBean(){
        return new SomeBean();
    }
}
