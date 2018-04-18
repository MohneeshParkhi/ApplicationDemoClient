package com.mohneesh.client.controller;

//import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohneesh.client.model.LoginModel;
import com.mohneesh.client.model.RegisterCustomer;
import com.mohneesh.client.service.LoginService;
import com.mohneesh.client.service.RegistrationService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApplicationController {
    @Autowired
	RegistrationService serviceAppReg;
	
    @Autowired
    LoginService serviceAppLog;
    
	@PostMapping(value="/saveCustInfo",consumes= {"application/Json"},produces= {"application/Json"})
	public ResponseEntity<?> getRegister(@RequestBody RegisterCustomer cust){
		return serviceAppReg.getRegistered(cust);
		
	}
	
	@PostMapping(value="/getValidate",consumes= {"application/Json"})
	public ResponseEntity<?> getLoginValidate(@RequestBody LoginModel login ){
		
		//System.out.println(login.getEmail());
		
		return serviceAppLog.getLoginValidate(login);
		

	}
	
	
}
