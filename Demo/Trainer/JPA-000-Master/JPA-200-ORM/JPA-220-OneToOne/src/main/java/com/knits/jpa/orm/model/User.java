package com.knits.jpa.orm.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Table;

import lombok.Data;

import com.knits.jpa.common.model.AbstractEntity;


 
@Table(name = "User")
@Data
public class User extends AbstractEntity {

	private Long id = null;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private int ranking = 0;
	private Address billingAddress;


	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
		// bidirectional
		/*
		this.billingAddress.setUtente(this);
		*/
	}



	

}
