package com.consistent.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.consistent.ws.soap.HelloWS;

@Configuration
public class WebServiceConfig {

	@Autowired
	private Bus bus;
	
	@Bean
	public Endpoint endPoint() {
		
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloWS());
		endpoint.publish("/hello");
		return endpoint;
	}
	
}
