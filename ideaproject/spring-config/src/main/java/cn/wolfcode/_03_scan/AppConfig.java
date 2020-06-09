package cn.wolfcode._03_scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration spring容器配置类标签
 * 如果类贴这个标签,表示该类为容器配置类
 * 等价于:applicationContext.xml
 *
 * @ComponentScan 组件扫描标签,按照指定的包扫描该包下(包括子包)的所有类
 *  如果类上面贴上了版型标签,会将这些类创建实例,交给spring容器
 *  等价<context:component-scan base-package="cn.wolfcode.crm.service.impl"/>
 * 注意:如果不明确指定包路径,默认扫描的是当前类所在的包及其子包
 */

@Configuration
@ComponentScan(basePackages = "cn.wolfcode._03_scan")
public class AppConfig {

}
