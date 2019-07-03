package com.knits.jpa.queries.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaContextUtil {

	private static EntityManagerFactory emFactory;
	
	
	public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName){
		if(emFactory==null){
			emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return emFactory;
	}
	

	
	public static void close(){
		emFactory.close();
	}

}
