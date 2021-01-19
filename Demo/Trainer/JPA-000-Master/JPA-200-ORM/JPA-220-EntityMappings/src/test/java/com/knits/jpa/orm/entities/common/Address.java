package com.knits.jpa.orm.entities.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * All ORM mapping unrelevant fields are defined here
 * @author stefano
 *
 */
@Data
@MappedSuperclass
public abstract class Address implements Serializable{

	@Column
	private String street;
	@Column
	private String zipcode;
	@Column
	private String city;
}
