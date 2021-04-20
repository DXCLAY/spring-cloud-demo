package com.dxclay.breakerdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "consul-discovery-demo", path = "/consul-demo", fallback = TestFallBackService.class)
public interface FeignTestService {

    @RequestMapping("/hello")
    public String callHello();

}
