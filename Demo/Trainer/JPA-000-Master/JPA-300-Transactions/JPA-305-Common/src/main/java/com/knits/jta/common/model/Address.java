package com.knits.jta.common.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Address extends AbstractEntity{

	private String street;
	private String zipcode;
	private String city;
}
