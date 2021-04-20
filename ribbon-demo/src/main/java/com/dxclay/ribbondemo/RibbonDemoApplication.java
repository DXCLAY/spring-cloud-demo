package com.dxclay.ribbondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RibbonDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(RibbonDemoApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        discoveryClient.getInstances("eureka-client").forEach(item -> {
            System.out.println("host: "+ item.getHost()+ " | port: "+ item.getPort());
        });

        for (int i = 0; i < 5; i++) {
            sendRequest();
        }
    }

    private void sendRequest(){
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://eureka-client/test/hello", HttpMethod.GET, null, String.class);
        System.out.println(responseEntity.toString());
    }

}
