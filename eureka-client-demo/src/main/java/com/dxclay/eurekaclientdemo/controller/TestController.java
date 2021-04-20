package com.dxclay.eurekaclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hello")
    public String hello(){
        return "hello eureka, the port is : " + port;
    }

}
