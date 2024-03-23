package com.avgkin.tacocloudplusserver.config.Nacos;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NacosConfig {
    @Bean("nacosRestTemplate")
    @LoadBalanced
    public RestTemplate nacosRestTemplate(){
        return new RestTemplate();
    }
}
