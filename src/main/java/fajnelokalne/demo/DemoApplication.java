package fajnelokalne.demo;

import fajnelokalne.demo.storage.StorageProperties;
import fajnelokalne.demo.storage.StorageService;
import io.springlets.format.config.EnableSpringletsEntityFormatWebSupport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableSpringletsEntityFormatWebSupport
@EnableConfigurationProperties(StorageProperties.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
