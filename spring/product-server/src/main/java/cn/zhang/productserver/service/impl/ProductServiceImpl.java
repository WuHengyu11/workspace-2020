package cn.zhang.productserver.service.impl;

import cn.zhang.springcloud.domain.Product;
import cn.zhang.productserver.mapper.ProductMapper;
import cn.zhang.productserver.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    @Override
    public Product get(Long id) {
        return productMapper.get(id);
    }
}
