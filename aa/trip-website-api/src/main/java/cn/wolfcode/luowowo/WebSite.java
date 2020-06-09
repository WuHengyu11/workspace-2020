package cn.wolfcode.luowowo;

import cn.wolfcode.luowowo.interceptor.BrushProofInterceptor;
import cn.wolfcode.luowowo.interceptor.CheckLoginInterceptor;
import cn.wolfcode.luowowo.interceptor.SignInterceptor;
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
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class WebSite implements WebMvcConfigurer {

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
    //拦截器对象
    @Bean
    public CheckLoginInterceptor checkLoginInterceptor(){
        return new CheckLoginInterceptor();
    }


    @Bean
    public BrushProofInterceptor brushProofInterceptor(){
        return  new BrushProofInterceptor();
    }

    @Bean
    public SignInterceptor signInterceptor(){
        return  new SignInterceptor();
    }



    //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/userLogin")
                .excludePathPatterns("/userRegist")
                .excludePathPatterns("/sendVerifyCode")
                .excludePathPatterns("/checkPhone")
        ;

        //防刷操作
        //registry.addInterceptor(brushProofInterceptor())
                //.addPathPatterns("/**");

        //签名
        //registry.addInterceptor(signInterceptor())
                //.addPathPatterns("/**");


    }


    //用户参数解析器
    @Bean
    public UserInfoArgumentResolver userInfoArgumentResolver(){
        return new UserInfoArgumentResolver();
    }

    //将自定义参数解析器加入springmvc中
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver());
    }

    public static void main(String[] args) {
        //解决netty冲突
        //System.setProperty("es.set.netty.runtime.available.processors", "false");

        SpringApplication.run(WebSite.class, args);
    }
}
