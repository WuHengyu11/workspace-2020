package cn.wolfcode.fegin;

import cn.wolfcode.service.IProductService;
import cn.wolfcode.springcloud.domain.Product;
import cn.wolfcode.springcloud.fegin.ProductFeginApi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductFeginClient implements ProductFeginApi {
    @Autowired
    private IProductService service;
    @Value("${server.port}")
    private String port;

    @Override
    public Product get(Long id) {
        Product product = service.get(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName() + ",data from" + port);
        return result;
    }
}
