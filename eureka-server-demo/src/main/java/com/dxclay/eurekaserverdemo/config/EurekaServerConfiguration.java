package com.dxclay.eurekaserverdemo.config;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaServer  //开启Eureka 服务端
public class EurekaServerConfiguration {
}
