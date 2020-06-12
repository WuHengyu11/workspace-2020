package cn.zhang.springcloud.service.impl;

import cn.zhang.springcloud.domain.Order;
import cn.zhang.springcloud.domain.Product;
import cn.zhang.springcloud.feign.ProductFeignApi;
import cn.zhang.springcloud.service.IOderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements IOderService{

    //@Autowired
    //private RestTemplate restTemplate;

    @Autowired
    private ProductFeignApi productFeignApi;
    @Override
    public Order get(Long productId, Long userId) {
        log.info("调用OrderController.get");
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setCreateTime(new Date());
        order.setUserId(userId);
        //Product product = restTemplate.getForObject("http://product-server/get?id="+productId,Product.class);
        Product product = productFeignApi.get(productId);
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        log.info("保存订单" + order);
        return order;
    }
}
