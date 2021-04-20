package com.dxclay.feigndemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "eureka-client", path = "/test")
public interface FeignTestService {

    @RequestMapping("/hello")
    public String callHello();

}
