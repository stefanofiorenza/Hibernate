package com.knits.jpa.orm.entities.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;


/**
 * All ORM mapping unrelevant fields are defined here
 * @author stefano
 *
 */

@Data
@MappedSuperclass
public abstract class User implements Serializable{

	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;
}
