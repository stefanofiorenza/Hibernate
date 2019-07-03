package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.Address;

@Service
public class AddressService {

	@PersistenceContext
	private EntityManagerFactory emf;
	
	
	public void saveAddressData(Address address){
		emf.createEntityManager();
		
		
		emf.close();		
	}
}
