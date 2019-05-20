package com.knits.jta.common.model;

import lombok.Data;


@Data
public class User extends AbstractEntity {

	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
 
}

