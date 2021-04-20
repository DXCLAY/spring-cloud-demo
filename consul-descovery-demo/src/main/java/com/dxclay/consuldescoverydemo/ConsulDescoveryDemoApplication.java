package com.dxclay.consuldescoverydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * consul 做注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulDescoveryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulDescoveryDemoApplication.class, args);
    }

}
