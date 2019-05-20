package com.knits.jta.common.model;

import lombok.Data;

@Data
public class Address extends AbstractEntity{

	private String street;
	private String zipcode;
	private String city;
}
