package com.knits.jta.demo.app.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knits.jta.common.model.Address;

public class AddressService {

	@PersistenceContext
	private EntityManager em;
	
	
	public void saveAddressData(Address address){
		em.persist(address);		
	}
}
