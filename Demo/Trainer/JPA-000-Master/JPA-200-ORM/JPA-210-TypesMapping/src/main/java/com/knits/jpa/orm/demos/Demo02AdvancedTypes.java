package com.knits.jpa.orm.demos;

import lombok.extern.slf4j.Slf4j;

import com.knits.jpa.common.utils.BasicDao;
import com.knits.jpa.common.utils.DemoCommonUtil;
import com.knits.jpa.orm.model.EntityWithAdvancedTypes;

@Slf4j
public class Demo02AdvancedTypes {

	private static final String PU_NAME="JPA-210-TypesMapping-PU";
	
	public static void main(String[] args) {

		BasicDao<EntityWithAdvancedTypes> dao = DemoCommonUtil.createBasicDao(EntityWithAdvancedTypes.class,PU_NAME);
			
		
		EntityWithAdvancedTypes entity =mockEntity();
		Long savedId =dao.save(entity);
		
		EntityWithAdvancedTypes foundInDb= dao.findById(EntityWithAdvancedTypes.class, savedId);
		
		log.info("Found IN DB: {}",foundInDb.toString());
		
		
		DemoCommonUtil.closeFactory();
	}
	
	
	private static EntityWithAdvancedTypes mockEntity(){
		EntityWithAdvancedTypes entity = new EntityWithAdvancedTypes();
		
		return entity;
	}

}
