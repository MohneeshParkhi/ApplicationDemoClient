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

import com.mohneesh.client.model.LoginModel;

@Service
public class LoginService {
    
	@Autowired
	DiscoveryClient discoveryClient;
	
	public ResponseEntity<?> getLoginValidate(LoginModel login) {
        
		List<ServiceInstance> listInstance = discoveryClient.getInstances("ApplicationDemoClient");
		
		ServiceInstance servInst = listInstance.get(0);
		
		String baseUrl = servInst.getUri().toString();
		
		String url = baseUrl+"/producer/applicationDemo/validateLogin";
		
	    System.out.println(url);	
		//String url = "http://localhost:8081/api/validatelogin";
		RestTemplate restTemp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> http = new HttpEntity(login, headers);
		ResponseEntity<?> response = restTemp.postForEntity(url, http, String.class);
		return response;
	}

}
