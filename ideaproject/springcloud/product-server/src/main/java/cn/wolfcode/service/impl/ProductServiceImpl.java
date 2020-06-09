package cn.wolfcode.service.impl;

import cn.wolfcode.mapper.ProductMapper;
import cn.wolfcode.service.IProductService;
import cn.wolfcode.springcloud.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
   @Autowired
   private ProductMapper productMapper;

    @Override
    public List<Product> listAll() {
        return productMapper.listAll();
    }

    @Override
    public Product get(Long id) {
        return productMapper.get(id);
    }
}
