package cn.wolfcode.luowowo;

import cn.wolfcode.luowowo.interceptor.CheckLoginInterceptor;
import cn.wolfcode.luowowo.interceptor.CheckLoginInterceptor2;
import cn.wolfcode.luowowo.resolver.UserInfoArgumentResolver;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class WebSite implements WebMvcConfigurer {
    //用户参数解析器
    @Bean
    public UserInfoArgumentResolver userInfoArgumentResolver(){
        return new UserInfoArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver());
    }

    //创建拦截器
    @Bean
    public CheckLoginInterceptor2 checkLoginInterceptor(){
        return new CheckLoginInterceptor2();
    }
    //springboot配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/users/checkPhone")
                .excludePathPatterns("/users/sendVerifyCode")
                .excludePathPatterns("/users/regist")
                .excludePathPatterns("/users/login");

    }

    //mongodb 去除_class属性
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {   mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }
        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
    }

    //跨域访问
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                //添加映射路径
                registry.addMapping("/**")
                        //放行哪些原始域
                        .allowedOrigins("*")
                        //是否发送Cookie信息
                        .allowCredentials(true)
                        //放行哪些原始域(请求方式)
                        .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                        //放行哪些原始域(头部信息)
                        .allowedHeaders("*")
                        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                        .exposedHeaders("Header1", "Header2");
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(WebSite.class, args);
    }
}
