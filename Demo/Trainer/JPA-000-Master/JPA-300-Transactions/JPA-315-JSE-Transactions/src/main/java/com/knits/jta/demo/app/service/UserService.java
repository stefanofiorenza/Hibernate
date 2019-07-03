package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.User;

@Service
public class UserService {

	@PersistenceContext
	private EntityManagerFactory emf;
	
	
	public void saveUserData(User userInStep1){
		emf.createEntityManager();
		
		
		emf.close();
	}
}
