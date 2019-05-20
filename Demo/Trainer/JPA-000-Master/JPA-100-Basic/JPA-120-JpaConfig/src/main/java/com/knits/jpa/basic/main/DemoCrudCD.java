package com.knits.jpa.basic.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;

import com.knits.jpa.common.model.CD;
import com.knits.jpa.common.utils.JpaContextUtil;

@Slf4j
public class DemoCrudCD {

	
	private static final String PU_NAME="JPA-120-JpaConfig-PU";
	
	public static void main(String[] args) {
		
		demo01SchemaCreation();
		demo02CRUDOperationOnCD();
	}

	

	private static void demo01SchemaCreation(){
		EntityManagerFactory emf=JpaContextUtil.getEntityManagerFactory(PU_NAME);
		JpaContextUtil.close();		
	}
	
	
	private static void demo02CRUDOperationOnCD(){
		EntityManagerFactory emf=JpaContextUtil.getEntityManagerFactory(PU_NAME);
		
		EntityManager em= emf.createEntityManager();
		CD aCdObject=createInMemoryCd();
		
		save(em,aCdObject);
		
		update(em,aCdObject);
		
		find(em,aCdObject);
		
		delete(em,aCdObject);
		
		JpaContextUtil.close();	
	}
	
	
	private static void delete(EntityManager em, CD aCdObject) {
	
		
	}


	private static void find(EntityManager em, CD aCdObject) {
		
		
	}


	private static void update(EntityManager em, CD aCdObject) {
	
		log.info("Current State in Memory: "+aCdObject.toString());
		aCdObject.setQuantity(30);		
		log.info("Update in Memory: "+aCdObject.toString());
		
		em.getTransaction().begin();
		
	
		em.merge(aCdObject);
		log.info("After Persist Id: "+aCdObject.getId());
		
		em.getTransaction().commit();
		
	}



	
	private static void save(EntityManager em , CD cdToSave){
		em.getTransaction().begin();
		
		log.info("Before Persist Id: "+cdToSave.getId());
		em.persist(cdToSave);
		log.info("After Persist Id: "+cdToSave.getId());
		
		em.getTransaction().commit();
	}
	
	
	private static CD createInMemoryCd(){
		CD mockCd = new CD();
		mockCd.setArtist("Bob Dylan");
		mockCd.setTitle("Empire Burlesque");
		mockCd.setCompany("RCA");
		mockCd.setPrice(10.90);
		mockCd.setYear(1966);
		mockCd.setCountry("USA");
		mockCd.setQuantity(12);		
		return mockCd;
	}
	
}
