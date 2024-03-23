package com.avgkin.tacocloudpluskitchen.service;

import com.avgkin.entity.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    boolean doCookTacos();
    boolean testCook(Order order);
    Order doCreateOrder(Order order);
}
