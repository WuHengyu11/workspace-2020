package cn.wolfcode.luowowo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MemberServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberServerApplication.class,args);
    }
}
