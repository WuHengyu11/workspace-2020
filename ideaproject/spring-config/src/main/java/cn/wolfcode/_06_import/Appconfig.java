package cn.wolfcode._06_import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Import:配置类引入表桥,配置类与配置类之间使用
 * 等价于: <import resource="classpath:applicationContext.xml"/>
 */
@Configuration
//@Import(OtherConfig.class)
@ImportResource("classpath:applicationContext.xml")
public class Appconfig {
    @Bean
    public SomeBean someBean(OtherBean otherBean){
        SomeBean someBean = new SomeBean();
        someBean.setOtherBean(otherBean);
        return someBean;
    }
}
