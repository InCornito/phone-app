package es.masmovil.phonecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhoneCatalogApplication {

	public static void main(final String[] args) {
		SpringApplication.run(PhoneCatalogApplication.class, args);
	}
}
