package es.urjc.code.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("es.urjc.code.pricing.infrastructure.adapter.repository.jpa")
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
