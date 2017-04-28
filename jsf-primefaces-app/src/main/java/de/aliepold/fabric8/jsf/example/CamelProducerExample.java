package de.aliepold.fabric8.jsf.example;

import java.util.HashMap;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;

public class CamelProducerExample {

	public CamelProducerExample(CamelContext camelContext){
		
		camelProducer = camelContext.createProducerTemplate();
	}	
	
	ProducerTemplate camelProducer;
	
	private String targetEndpointUri = "direct:submitItem";
	
	public String getTargetEndpointUri() {
		return targetEndpointUri;
	}

	public void setTargetEndpointUri(String targetEndpointUri) {
		this.targetEndpointUri = targetEndpointUri;
	}
	
	HashMap<String, Object> headers = new HashMap<String, Object>();
	
	public void setHeaders(HashMap<String, Object> headers) {
		this.headers = headers;
	}
	public HashMap<String, Object> getHeaders() {
		return headers;
	}	
	
	public void sendToCamelRoute(Object sendObject){
		
		try {
			
			Object responseFromBus = camelProducer.sendBodyAndHeaders(getTargetEndpointUri(), ExchangePattern.InOnly, sendObject, headers);
			
			Object o = new Object();
			System.out.println(responseFromBus + "" + o);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
