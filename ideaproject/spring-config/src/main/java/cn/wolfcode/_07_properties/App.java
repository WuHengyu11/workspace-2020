package cn.wolfcode._07_properties;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//使用javaconfig方式启动spring容器
@ContextConfiguration(classes = AppConfig.class)
public class App {
    @Autowired
    private ApplicationContext ctx;

    @Test
    public void testBean(){
        System.out.println(ctx.getBean(MyDataResource.class));
    }
}
