package cn.zhang.springcloud.controller;

import cn.zhang.springcloud.domain.Order;
import cn.zhang.springcloud.service.IOderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private IOderService oderService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @HystrixCommand(fallbackMethod = "saveFallback")
    @RequestMapping("/save")
    public Order saver(Long productId, Long userId, HttpServletRequest request){
        log.info("调用OrderController.save");
        return oderService.get(productId,userId);
    }
    public Order saveFallback(Long productId, Long UserId, HttpServletRequest request){
        System.out.println("兜底数据");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String key = "order-server";
                String value = stringRedisTemplate.opsForValue().get(key);
                if (!StringUtils.isEmpty(value)) {
                    System.out.println("已经发送过短信");
                } else {
                    System.out.println("order下订单服务失败，请查找原因.");
                    stringRedisTemplate.opsForValue().set(key, "save-order-fail", 20, TimeUnit.SECONDS);
                }
            }
            }).start();
        return new Order();
    }
}
