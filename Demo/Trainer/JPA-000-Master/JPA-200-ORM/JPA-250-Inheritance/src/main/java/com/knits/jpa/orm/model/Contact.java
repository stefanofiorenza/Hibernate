package com.knits.jpa.orm.model;

import lombok.Data;

@Data
public class Contact {

	private Long id = null;	
	private String firstname;
	private String lastname;
	private String email;
}
