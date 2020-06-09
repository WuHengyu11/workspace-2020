package cn.wolfcode.springcloud.fegin;

import cn.wolfcode.springcloud.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-server")
public interface ProductFeginApi {
    @RequestMapping("/get")
    Product get(@RequestParam Long id);
}
