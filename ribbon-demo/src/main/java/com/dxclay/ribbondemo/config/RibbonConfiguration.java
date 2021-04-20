package com.dxclay.ribbondemo.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 这里使用了restTemplate 增强的方式调用服务
 */
@Configuration
@EnableDiscoveryClient
public class RibbonConfiguration {
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(){
        PoolingHttpClientConnectionManager httpClientConnectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);

        httpClientConnectionManager.setMaxTotal(200);
        //最大处理的请求数量
        httpClientConnectionManager.setDefaultMaxPerRoute(20);

        //http 请求客户端
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @LoadBalanced //开启负载均衡
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder
                .setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(500))
                .requestFactory(this::requestFactory)
                .build();
    }
}
