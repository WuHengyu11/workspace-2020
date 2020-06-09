package cn.wolfcode.service;

import cn.wolfcode.springcloud.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> listAll();
    Product get(Long id);
}
