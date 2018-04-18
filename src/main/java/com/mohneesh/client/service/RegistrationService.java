package com.mohneesh.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mohneesh.client.model.RegisterCustomer;
//import com.netflix.appinfo.InstanceInfo;

//import com.netflix.discovery.shared.Application;

@Service
public class RegistrationService {

	/*
	 * private RestTempate rest;
	 * 
	 * public RestTemplate() {
	 * 
	 * }
	 */
	@Autowired
	DiscoveryClient discoveryClient;

	public ResponseEntity<?> getRegistered(RegisterCustomer cust) {
		List<?> listServices = discoveryClient.getInstances("ApplicationDemoClient");
		ServiceInstance instance = (ServiceInstance) listServices.get(0);
		String url = instance.getUri().toString();

		String baseUrl = url + "/producer/applicationDemo/";
        
		RestTemplate restTemp = new RestTemplate();

		// String baseUrl = "http://localhost:8081/applicationDemo/";
		//System.out.println(baseUrl);

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity = new HttpEntity(cust,header);
		
		ResponseEntity resp = restTemp.postForEntity(baseUrl + "saveCustInfo", entity, String.class);

		return resp;
	}

}
