package cn.wolfcode.springcloud.service;

import cn.wolfcode.springcloud.domain.Order;

public interface IOrderService {
    Order save(Long userId, Long productId);
}
