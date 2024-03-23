package com.avgkin.tacocloudplusserver.controller;

import com.avgkin.entity.dto.UserDTO;
import com.avgkin.entity.po.Taco;
import com.avgkin.tacocloudplusserver.result.CommonResult;
import com.avgkin.tacocloudplusserver.result.CommonResults;
import com.avgkin.tacocloudplusserver.service.OrderService;
import com.avgkin.tacocloudplusserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/cook")
    @PreAuthorize("hasRole('USER')")
    public CommonResult<Void> doSendOrder(@RequestParam String tacoName){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = redisUtil.getValue("auth",userDetails.getUsername());
        orderService.sendOrder(List.of(new Taco(tacoName)),userDTO.getId());
        return CommonResults.success();
    }
    @GetMapping("/ping")
    @PreAuthorize("hasAuthority('PING')")
//    @PreAuthorize("hasRole('SSSS')")
    public String ping(){
        return orderService.ping();
    }
}
