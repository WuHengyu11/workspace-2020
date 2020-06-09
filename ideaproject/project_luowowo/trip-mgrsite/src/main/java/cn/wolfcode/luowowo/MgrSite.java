package cn.wolfcode.luowowo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //启动springboot任务的支持
public class MgrSite {
    public static void main(String[] args) {
        SpringApplication.run(MgrSite.class,args);
    }
}
