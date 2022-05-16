package com.fdu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.fdu.mall.dao")
@EnableDiscoveryClient
public class UserService {

    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }

}
