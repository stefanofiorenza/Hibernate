package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.Address;

@Service
@Transactional
public class AddressService {

	@PersistenceContext
	private EntityManager em;
	
	
	public void saveAddressData(Address address){
		em.persist(address);		
	}
}
