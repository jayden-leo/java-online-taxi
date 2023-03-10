package com.jayden.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.jayden.serviceorder.mapper")
public class ServiceOrderInfoApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceOrderInfoApplication.class);
    }
}
