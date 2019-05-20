package com.knits.jpa.orm.demos;

import lombok.extern.slf4j.Slf4j;

import com.knits.jpa.common.utils.BasicDao;
import com.knits.jpa.common.utils.DemoCommonUtil;
import com.knits.jpa.orm.model.EntityWithBasicTypes;

@Slf4j
public class Demo01BasicTypes {

	private static final String PU_NAME="JPA-210-TypesMapping-PU";
	
	public static void main(String[] args) {

		BasicDao<EntityWithBasicTypes> dao = DemoCommonUtil.createBasicDao(EntityWithBasicTypes.class,PU_NAME);
		
		
		EntityWithBasicTypes entity =mockEntity();
		Long savedId =dao.save(entity);
		
		EntityWithBasicTypes foundInDb= dao.findById(EntityWithBasicTypes.class, savedId);
		
		log.info("Found IN DB: {}",foundInDb.toString());
		
		
		DemoCommonUtil.closeFactory();	

	}
	
	
	private static EntityWithBasicTypes mockEntity(){
		EntityWithBasicTypes mockEntity= new EntityWithBasicTypes();
		mockEntity.setABooleanField(false);
		mockEntity.setAnDoubleField(9.50);
		mockEntity.setAnIntegerField(4);
		mockEntity.setAVarCharField("Some Mock Test");
		return mockEntity;
	}


}
