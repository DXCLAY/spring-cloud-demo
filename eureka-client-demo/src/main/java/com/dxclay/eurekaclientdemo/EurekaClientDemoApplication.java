package com.dxclay.eurekaclientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Eureka 做服务发现的客户端
 */
@SpringBootApplication
public class EurekaClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientDemoApplication.class, args);
    }

}
