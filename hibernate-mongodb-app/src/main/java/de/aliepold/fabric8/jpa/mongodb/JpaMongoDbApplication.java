package de.aliepold.fabric8.jpa.mongodb;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaMongoDbApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaMongoDbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaMongoDbApplication.class, args);
	}

	@PostConstruct
	public void startProcess() {

	}
}
