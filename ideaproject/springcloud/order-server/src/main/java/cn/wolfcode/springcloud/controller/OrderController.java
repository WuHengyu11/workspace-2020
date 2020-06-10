package cn.wolfcode.springcloud.controller;

import cn.wolfcode.springcloud.domain.Order;
import cn.wolfcode.springcloud.service.IOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {
    @Autowired
    private IOrderService orderService;

    @HystrixCommand(fallbackMethod = "saverFail")
    @RequestMapping("/save")
    public Order save(Long userId, Long productId){
        //模拟出错
        //int i = 1 / 0;
        return orderService.save(userId,productId);
    }

    public Order saverFail(Long userId, Long productId){
        System.out.println("返回兜底数据");
        return new Order();
    }
}
