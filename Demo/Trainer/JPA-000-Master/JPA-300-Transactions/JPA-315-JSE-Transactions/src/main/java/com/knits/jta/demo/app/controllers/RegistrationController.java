package com.knits.jta.demo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knits.jta.common.beans.ResponseBean;
import com.knits.jta.common.model.Address;
import com.knits.jta.common.model.Qualification;
import com.knits.jta.common.model.User;
import com.knits.jta.common.utils.Mocks;
import com.knits.jta.demo.app.service.AddressService;
import com.knits.jta.demo.app.service.QualificationService;
import com.knits.jta.demo.app.service.RegistrationService;
import com.knits.jta.demo.app.service.UserService;

@Component
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private QualificationService qualificationService;
	
	
	@Autowired
	private RegistrationService registrationService;
	
	public ResponseBean<Integer> registerUser(){
		
		User user = Mocks.mockUser();
		userService.saveUserData(user);
		
		Address address = Mocks.mockAddress();
		addressService.saveAddressData(address);
		
		Qualification qualification = Mocks.mockQualification();
		qualificationService.saveQualificationData(qualification);
		
		registrationService.completeProcess();
		
		return new ResponseBean<Integer>(1024);
	}
}
