package cn.wolfcode.mapper;

import cn.wolfcode.springcloud.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ProductMapper {
    public static HashMap<Long,Product> map = new HashMap<>();
    static {
        Product p1 = new Product(1L,"小米手机",2999,10);
        Product p2 = new Product(2L,"苹果手机",2999,10);
        Product p3 = new Product(3L,"魅族手机",2999,10);
        Product p4 = new Product(4L,"华为手机",2999,10);
        Product p5 = new Product(5L,"红米手机",2999,10);
        map.put(p1.getId(),p1);
        map.put(p2.getId(),p2);
        map.put(p3.getId(),p3);
        map.put(p4.getId(),p4);
        map.put(p5.getId(),p5);
    }
    public List<Product> listAll(){
        return new ArrayList<>(map.values());
    }
    public Product get(Long id){
        return map.get(id);
    }
}
