package cn.zhang.productserver.feign;

import cn.zhang.productserver.service.IProductService;
import cn.zhang.springcloud.domain.Product;
import cn.zhang.springcloud.feign.ProductFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class ProductFeignClient implements ProductFeignApi {

    @Autowired
    private IProductService productService;
    //@Value("${server.port}")
    private String port;

    @Override
    public Product get(Long id) {

//        try{
//            System.out.println("请求商品服务");
//            TimeUnit.SECONDS.sleep(2);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        log.info("调用ProductFeignClient.get");
        Product product = productService.get(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName()+",data fuck"+port);
        return result;
    }
}
