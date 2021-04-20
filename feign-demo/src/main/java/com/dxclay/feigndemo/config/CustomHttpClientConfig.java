package com.dxclay.feigndemo.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CustomHttpClientConfig {

    @Bean
    public CloseableHttpClient httpClient(){
        return HttpClients.custom()
                .setConnectionTimeToLive(30, TimeUnit.SECONDS)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
    }
}
