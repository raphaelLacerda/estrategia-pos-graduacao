package br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@EnableDiscoveryClient // adicionado
@EnableZuulProxy
@SpringBootApplication
public class ZuulGateway {
    public static void main(String[] args) {

        SpringApplication.run(ZuulGateway.class, args);
    }
}
