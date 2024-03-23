package com.avgkin.tacocloudplusserver.service;

import com.avgkin.entity.po.Taco;

import java.util.List;

public interface OrderService {
    Boolean sendOrder(List<Taco> tacos, Long userId);
    String ping();
}
