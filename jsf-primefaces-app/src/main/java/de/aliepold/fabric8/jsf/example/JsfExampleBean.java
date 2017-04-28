package de.aliepold.fabric8.jsf.example;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean(name = "jsfExampleBean", eager = true)
@RequestScoped
public class JsfExampleBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String nameItem = "test";

	public String getPodIp() throws UnknownHostException {
		
		InetAddress ia = InetAddress.getLocalHost();
		
		return   "Host: " + ia.getHostName() + " Pod-Ip:" + ia.getHostAddress();
	}

	public String getNameItem() {
		return nameItem;
	}

	public void setNameItem(String nameItem) {
		this.nameItem = nameItem;
	}

	public String getItWorks() {
		return "Jsf-App - It works!";
	}
	
	public String sendIt(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
		   CamelContext camelContext = (CamelContext) context.getBean("jsfCamelContext");        
		   System.out.println(camelContext.getEndpoints()); 
		
		   CamelProducerExample camel = new CamelProducerExample(camelContext);
		   
		camel.sendToCamelRoute(getNameItem());
		
		return "done";
	}
}
