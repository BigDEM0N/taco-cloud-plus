package com.avgkin.tacocloudplusserver.service.impl;

import com.avgkin.entity.po.Order;
import com.avgkin.entity.po.Taco;
import com.avgkin.feign.OrderClient;
import com.avgkin.tacocloudplusserver.service.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;
    @Autowired
    private OrderClient orderClient;
    @Override
    public Boolean sendOrder(List<Taco> tacos, Long userId) {
        Order order = new Order(userId,tacos);
        try{
            CompletableFuture<SendResult<String,Order>> cf =  kafkaTemplate.send("order",order);
            System.out.println(cf.get().getRecordMetadata().topic());

//            nacosRestTemplate.postForObject("http://tc-kitchen/api/kitchen/Order",order,Order.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String ping() {
        try {
            String s = orderClient.ping();
            System.out.println(s);
            return s;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
