package cn.wolfcode._04_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    /**
     * bean xml配置           实例方法配置
     *      id                  实例方法名
     *      name                @Bean的name属性
     *      destroy-method      @Bean的destroyMethod属性
     *      init-method         @Bean的initMethod属性
     *      scope               @Scope
     * @return
     */
    @Scope("singleton")
    @Bean(name = "sb",destroyMethod = "destroy",initMethod = "init")
    public SomeBean someBean(){
        return new SomeBean();
    }
}
