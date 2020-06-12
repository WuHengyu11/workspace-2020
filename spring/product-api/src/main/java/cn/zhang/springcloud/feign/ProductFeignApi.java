package cn.zhang.springcloud.feign;

import cn.zhang.springcloud.domain.Product;
import cn.zhang.springcloud.feign.hystrix.ProductFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-server",fallback = ProductFeignHystrix.class)
//@FeignClient(name = "product-server")
public interface  ProductFeignApi {

    @RequestMapping("/get")
    public abstract Product get(@RequestParam("id") Long id);
}
