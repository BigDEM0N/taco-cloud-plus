package com.avgkin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("tc-kitchen")
public interface OrderClient {
    @GetMapping("api/kitchen/ping")
    String ping();
}
