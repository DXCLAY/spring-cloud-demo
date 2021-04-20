package com.dxclay.feigndemo;

import com.dxclay.feigndemo.service.FeignTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 使用feign 做服务的发现和调用
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务发现
@EnableFeignClients    //开启feign功能
public class FeignDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(FeignDemoApplication.class, args);
    }

    @Autowired
    private FeignTestService feignTestService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 5; i++) {
            String s = feignTestService.callHello();
            System.out.println(i + " times result: " + s);
        }
    }
}
