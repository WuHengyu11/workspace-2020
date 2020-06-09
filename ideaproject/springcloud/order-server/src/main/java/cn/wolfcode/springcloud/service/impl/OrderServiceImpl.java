package cn.wolfcode.springcloud.service.impl;

import cn.wolfcode.springcloud.domain.Order;
import cn.wolfcode.springcloud.domain.Product;
import cn.wolfcode.springcloud.fegin.ProductFeginApi;
import cn.wolfcode.springcloud.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private ProductFeginApi productFeginApi;

    @Override
    public Order save(Long userId, Long productId) {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setCreateTime(new Date());
        order.setUserId(userId);
        //Product product = template.getForObject("http://product-server/get?id=" +productId,Product.class);
        Product product = productFeginApi.get(productId);
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        System.out.println("执行保存订单操作");
        return order;
    }
}
