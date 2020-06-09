package cn.wolfcode.springcloud.controller;

import cn.wolfcode.springcloud.domain.Order;
import cn.wolfcode.springcloud.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("/save")
    public Order save(Long userId, Long productId){
        return orderService.save(userId,productId);
    }
}
