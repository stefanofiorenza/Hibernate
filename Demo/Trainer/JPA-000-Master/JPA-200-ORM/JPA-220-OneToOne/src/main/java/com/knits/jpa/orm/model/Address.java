package com.knits.jpa.orm.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Table;

import com.knits.jpa.common.model.AbstractEntity;

import lombok.Data;


@Table(name = "Address")
@Data
public class Address extends AbstractEntity{ 

	private Long id;
	private String street;
	private String zipcode;
	private String city;
	private User user;
		
}
