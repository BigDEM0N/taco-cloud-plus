package com.avgkin.tacocloudpluskitchen.controller;

import com.avgkin.entity.po.Order;
import com.avgkin.tacocloudpluskitchen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kitchen")
public class KitchenController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/doCook")
    public boolean doCook(){
        orderService.doCookTacos();
        return true;
    }
    @GetMapping("/doCook1")
//    @KafkaListener(topics = "order")
    public boolean testCook(Order order){
        orderService.testCook(order);
        return true;
    }
    @PostMapping("/Order")
    public Order doCreateOrder(@RequestBody Order order){
        return orderService.doCreateOrder(order);
    }
    @GetMapping("/ping")
    public String ping(){
        System.out.println("ping");
        return "pong";
    }
}
