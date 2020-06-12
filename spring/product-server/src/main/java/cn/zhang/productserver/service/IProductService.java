package cn.zhang.productserver.service;

import cn.zhang.springcloud.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> selectAll();
    Product get(Long id);
}
