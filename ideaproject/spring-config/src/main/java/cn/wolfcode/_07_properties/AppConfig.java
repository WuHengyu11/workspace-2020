package cn.wolfcode._07_properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @PropertySource 属性资源引入注解
 * 将配置文件引入到spring容器中
 * 等价:<context:property-placeholder location="classpath:db.properties"/>
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {
   /*
   @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    */

   //
    @Autowired
    private Environment ev;

    @Bean
    public MyDataResource myDataResource(){
        //MyDataResource myDataResource = new MyDataResource(url,driverClassName,username,password);
        MyDataResource myDataResource = new MyDataResource(
                ev.getProperty("jdbc.url"),
                ev.getProperty("jdbc.driverClassName"),
                ev.getProperty("jdbc.username"),
                ev.getProperty("jdbc.password")
        );
        return myDataResource;
    }
}
