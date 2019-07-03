package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.Qualification;

@Service
public class QualificationService {
	
	
	@PersistenceContext
	private EntityManagerFactory emf;
	
	
	public void saveQualificationData(Qualification qualification){
		emf.createEntityManager();
		
		
		emf.close();	
	}

}
