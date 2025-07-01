package io.github.Yuurim98.mojip_go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MojipGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MojipGoApplication.class, args);
	}

}
