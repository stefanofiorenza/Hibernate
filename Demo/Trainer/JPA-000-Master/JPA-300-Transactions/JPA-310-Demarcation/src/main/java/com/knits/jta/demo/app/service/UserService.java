package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.User;

@Service
@Transactional
public class UserService {

	
	@PersistenceContext
	private EntityManager em;
	
	
	public void saveUserData(User userInStep1){
		em.persist(userInStep1);		
	}
}
