package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.User;


@Service
@Slf4j
public class RegistrationService {

	@PersistenceContext
	private EntityManagerFactory emf;
		
	
	public void completeProcess(){
		log.info("Perform some checks and possibly validate saved data");
		//throw new RuntimeException("Validation Checks failed");
	}
	

}
