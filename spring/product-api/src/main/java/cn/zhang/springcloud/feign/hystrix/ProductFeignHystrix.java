package cn.zhang.springcloud.feign.hystrix;

import cn.zhang.springcloud.domain.Product;
import cn.zhang.springcloud.feign.ProductFeignApi;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignHystrix implements ProductFeignApi {

    @Override
    public Product get(Long id) {
        System.out.println("返回默认的兜底数据");
        Product product = new Product();
        product.setName("默认商品");
        product.setPrice(-1);
        return product;
    }
}
