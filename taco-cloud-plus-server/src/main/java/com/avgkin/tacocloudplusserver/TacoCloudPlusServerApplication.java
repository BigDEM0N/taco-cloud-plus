package com.avgkin.tacocloudplusserver;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.avgkin")
@EnableFeignClients(basePackages = "com.avgkin.feign")
public class TacoCloudPlusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudPlusServerApplication.class, args);
    }

}
