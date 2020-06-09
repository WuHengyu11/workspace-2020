package cn.wolfcode._05_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OtherBean otherBean(){
        System.out.println("---------------------");
        return new OtherBean();
    }
    /**
    @Bean
    public SomeBean someBean(){
        SomeBean someBean = new SomeBean();
        //方式一:调用实例方法
        //强调:使用方法调用,不是new OtherBean();
        //等价:<property name="otherBean" ref="otherBean"></property>
        //原理:spring容器在调用实例方法时,根据解析方法返回复习,得到返回对象类型
        //判断该类型实例对象是否存在容器中如果不存在执行实例方法,将返回对象实例交给容器管理
        //如果该实例已经存在,直接从容器中拿已经存在的实例对象方法,不执行实例方法
        otherBean();
        otherBean();
        otherBean();
        otherBean();
        otherBean();
        someBean.setOtherBean(otherBean());
        return someBean;
    }
     */
    @Bean
    public SomeBean someBean(OtherBean otherBean){
        SomeBean someBean = new SomeBean();
        someBean.setOtherBean(otherBean);
        return someBean;
    }
}
