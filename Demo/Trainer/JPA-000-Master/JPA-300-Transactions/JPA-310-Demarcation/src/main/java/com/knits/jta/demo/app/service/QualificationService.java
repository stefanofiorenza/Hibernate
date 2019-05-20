package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knits.jta.common.model.Qualification;

public class QualificationService {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void saveQualificationData(Qualification qualification){
		em.persist(qualification);		
	}

}
