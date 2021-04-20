package com.dxclay.consuldescoverydemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consul-demo")
public class ConsulTestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("hello")
    public String hello(){
        return "hello consul, the port is "+ port;
    }

}
