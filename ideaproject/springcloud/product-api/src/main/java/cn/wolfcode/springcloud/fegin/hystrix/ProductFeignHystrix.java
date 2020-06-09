package cn.wolfcode.springcloud.fegin.hystrix;

import cn.wolfcode.springcloud.domain.Product;
import cn.wolfcode.springcloud.fegin.ProductFeginApi;

public class ProductFeignHystrix implements ProductFeginApi {
    @Override
    public Product get(Long id) {
        return null;
    }
}
