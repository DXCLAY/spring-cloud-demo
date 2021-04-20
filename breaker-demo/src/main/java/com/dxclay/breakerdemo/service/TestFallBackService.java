package com.dxclay.breakerdemo.service;

import org.springframework.stereotype.Component;

@Component
public class TestFallBackService implements FeignTestService{
    @Override
    public String callHello() {
        return "host is dead. please call after 3 minute";
    }
}
