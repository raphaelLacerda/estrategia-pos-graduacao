package br.estrategia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication()
//@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableFeignClients
public class ApplicationBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBootstrap.class, args);
	}


//	@Bean @LoadBalanced
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}



}
