package com.dxclay.breakerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 自定义一个服务熔断器
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableAspectJAutoProxy
@EnableHystrix
public class BreakerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakerDemoApplication.class, args);
    }

}
