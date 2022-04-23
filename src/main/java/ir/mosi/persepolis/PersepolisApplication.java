package ir.mosi.persepolis;

import ir.mosi.persepolis.client.Client;
import ir.mosi.persepolis.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class PersepolisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersepolisApplication.class, args);
	}

}
