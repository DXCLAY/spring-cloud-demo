package com.dxclay.breakerdemo.controller;

import com.dxclay.breakerdemo.service.FeignTestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("breaker-test")
public class BreakerTestController {

    /**
     * feign 远程调用
     */
    @Autowired
    private FeignTestService feignTestService;

    @RequestMapping("hello")
//    @HystrixCommand(fallbackMethod = "fallBackTest")
    public String hello(){
        return feignTestService.callHello();
    }

    public String fallBackTest(){
        return "host is dead. please call after 3 minute";
    }

}
