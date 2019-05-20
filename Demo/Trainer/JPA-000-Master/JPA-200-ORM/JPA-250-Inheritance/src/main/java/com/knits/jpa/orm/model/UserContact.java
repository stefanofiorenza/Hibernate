package com.knits.jpa.orm.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserContact extends Contact{

	private String username;
	private String password;
	private Date created;
}
