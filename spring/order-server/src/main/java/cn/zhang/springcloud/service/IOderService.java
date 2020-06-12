package cn.zhang.springcloud.service;

import cn.zhang.springcloud.domain.Order;

public interface IOderService {
    Order get(Long productId,Long userId);
}
