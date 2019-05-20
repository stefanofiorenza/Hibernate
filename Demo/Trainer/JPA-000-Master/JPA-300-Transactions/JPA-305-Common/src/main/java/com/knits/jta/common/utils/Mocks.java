package com.knits.jta.common.utils;

import com.knits.jta.common.model.Address;
import com.knits.jta.common.model.Qualification;
import com.knits.jta.common.model.ReservedSeat;
import com.knits.jta.common.model.User;

public class Mocks {

	
	public static User mockUser(){
		User mock = new User();
		mock.setFirstName("mockFirstName");
		mock.setLastName("mockLastName");
		mock.setEmail("someEmail@email.com");
		mock.setTelephone("09573625554");
		return mock;
	}
	
	public static Address mockAddress(){
		Address mock = new Address();
		mock.setCity("Some Beautiful City");
		mock.setStreet("Some Lovely Street 12");
		mock.setZipcode("80046");
		return mock;
	}
	
	public static Qualification mockQualification(){
		Qualification mock = new Qualification();
		mock.setGrade(100);
		mock.setInstitution("Some Respected Institution");
		mock.setQualificationType("Master");
		return mock;
		
	}

	public static ReservedSeat mockReservedSeat() {
		ReservedSeat reservedSeat = new ReservedSeat();
		reservedSeat.setCategory("someMockCategory");
		reservedSeat.setDocumentNumber("someMockDocumentNumber");
		reservedSeat.setPlaceNumber(199);
		return reservedSeat;
	}
}
