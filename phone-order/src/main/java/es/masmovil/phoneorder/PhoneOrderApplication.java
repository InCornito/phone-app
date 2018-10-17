package es.masmovil.phoneorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhoneOrderApplication {

	public static void main(final String[] args) {
		SpringApplication.run(PhoneOrderApplication.class, args);
	}
}
