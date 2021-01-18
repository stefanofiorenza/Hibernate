package com.knits.jpa.orm.entities.one.to.many.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;


import lombok.Data;


public class TestOneToMany02_LinkTable extends AbstractJPAProgrammaticBootstrapTest{

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {
	        		Address.class,
	        		User.class
	        };
	    }
	   
	   
	   @Test
	   public void testCrud() {
		   doInJPATransaction(entityManager -> {
			  
			   Address billingAddress = new Address();
			   billingAddress.setCity("Roma");
			   billingAddress.setStreet("Piazza di Spagna");
			   billingAddress.setZipcode("00100");
			   
			   User user = new User();
			   user.setFirstname("Stefano");
			   user.setLastname("Fiorenza");
			   user.setEmail("stefano@email.it");
			   user.setBillingAddress(billingAddress);
			  
			  
			   entityManager.persist(billingAddress); //address is parent of this relationship
			   entityManager.persist(user);
			   
		   });
	   }
	   
	   
	   @Table(name = "Address")
	   @Entity
	   @Data
	   public static class Address extends com.knits.jpa.orm.entities.common.Address implements Serializable{ 

		   	@Id
		   	@GeneratedValue(strategy = GenerationType.AUTO)
		   	private Long id;
		 
		   	
		   	@OneToOne(mappedBy = "billingAddress")
		   	private User user;
	   		
	   }


	   @Table(name = "User")
	   @Entity
	   @Data
	   public static class User extends com.knits.jpa.orm.entities.common.User implements Serializable {
	   
		   		
		   	@Id
		   	@OneToOne
		   	@JoinColumn(name = "id")
		   	private Address billingAddress;

	   }
}



