package com.knits.jpa.orm.demos;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo01CrudOperations {

	private static final String PU_NAME="JPA-250-Inheritance-PU";
	
	public static void main(String[] args) {

		/*
		BasicDao<User> dao = DemoCommonUtil.createBasicDao(User.class,PU_NAME);
				
		User userInMemory =mockUser();
		Address addressInMemory= mockAddress();
		
		Long savedId =dao.save(userInMemory);
		
		User foundInDb= dao.findById(User.class, savedId);
		
		log.info("Found IN DB: {}",foundInDb.toString());
		
		
		DemoCommonUtil.closeFactory();	
*/
	}
	
	/*
	private static User mockUser(){
		User mockEntity= new User();
		return mockEntity;
	}
	
	
	private static Address mockAddress(){
		Address entity = new Address();		
		return entity;
	}

*/
}
