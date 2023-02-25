package com.jayden.serviceprice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.jayden.serviceprice.mapper")
public class ServicePriceApplication {
    public static void main(String[] args){
        SpringApplication.run(ServicePriceApplication.class);
    }
}
