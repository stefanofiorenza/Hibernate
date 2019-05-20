package com.knits.jpa.common.utils;

import com.knits.jpa.common.model.AbstractEntity;

public class DemoCommonUtil {
		
	public static <T extends AbstractEntity> BasicDao<T> createBasicDao(Class<T> entityClass, String persistenceUnit ){
	
		BasicDao<T> dao = new BasicDao<T>();
		dao.setEm(JpaContextUtil.getEntityManagerFactory(persistenceUnit).createEntityManager());
		return dao;
	}
	
	
	public static void closeFactory(){
		JpaContextUtil.close();	
	}
	
}
