package de.aliepold.fabric8.bpmn.example;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class CamundaSpringBootExampleApplication{

	 private static final Logger LOGGER = LoggerFactory.getLogger(CamundaSpringBootExampleApplication.class);
	
	@Autowired
	private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(CamundaSpringBootExampleApplication.class, args);
	}

	@PostConstruct
	public void startProcess() {
		
		runtimeService.startProcessInstanceByKey("simpleBpmRequest");
		LOGGER.info("Postconstruct aufgerufen.");
	}
}
