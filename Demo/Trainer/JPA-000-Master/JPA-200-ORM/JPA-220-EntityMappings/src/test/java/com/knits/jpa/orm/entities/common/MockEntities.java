package com.knits.jpa.orm.entities.common;

public class MockEntities {

	
	public static void fillData(Address address) {
		address.setCity("Roma");
		address.setStreet("Piazza di Spagna");
		address.setZipcode("00100");
	}
	
	public static void fillData(User user) {
		user.setFirstname("Stefano");
		user.setLastname("Fiorenza");
		user.setEmail("stefano@email.it");
	}
	
}
