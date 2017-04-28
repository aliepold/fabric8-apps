package de.aliepold.fabric8.bpmn.example.tasks;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BpmRequestService implements JavaDelegate {

  private static final Logger LOGGER = LoggerFactory.getLogger(BpmRequestService.class);

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    
	  LOGGER.info("Bpmn-Processor wurde ausgeführt");
  }

}