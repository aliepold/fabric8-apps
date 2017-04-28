package de.aliepold.fabric8.bpmn.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.camel.Message;

public class CamundaCamelProcessor implements Processor {

	public CamundaCamelProcessor() {

	}
	
	@Autowired
	private RuntimeService runtimeService;

	public void process(Exchange exchange) throws Exception {

		Message message = exchange.getIn();
		
		runtimeService.startProcessInstanceByKey("bpmRequest");
		
		exchange.getOut().setHeaders(message.getHeaders());
		exchange.getOut().setBody("Message ist durch Processor");
	}
}
