package com.jayden.serviceverifycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerifyCodeApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceVerifyCodeApplication.class);
    }
}
