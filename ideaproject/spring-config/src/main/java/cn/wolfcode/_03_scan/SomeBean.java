package cn.wolfcode._03_scan;
//需求:使用注解方式自动让spring容器管理对象,而不需要手动配置

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//版型标签:让spring容器自动扫描并创建对象:ioc
//@Controller //表示层
//@Service   //业务层
//@Repository //持久层
@Component //兜底
public class SomeBean {

}
