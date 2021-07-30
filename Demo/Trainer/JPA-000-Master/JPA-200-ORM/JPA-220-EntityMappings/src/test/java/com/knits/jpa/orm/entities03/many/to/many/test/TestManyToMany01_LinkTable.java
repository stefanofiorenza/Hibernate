package com.knits.jpa.orm.entities03.many.to.many.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;


import lombok.Data;


public class TestManyToMany01_LinkTable extends AbstractJPAProgrammaticBootstrapTest{

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


			   //connect entities:
			   billingAddress.getUsers().add(user);
			  
			  //1) Owner of relationship??
			   entityManager.persist(billingAddress);
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

			@ManyToMany(cascade = CascadeType.ALL)
			@JoinTable(name = "user_address",
					joinColumns = @JoinColumn(name = "address_id"),
					inverseJoinColumns = @JoinColumn(name = "user_id")
			)
			@Getter
			private List<User> users= new ArrayList<>();
	   }


	   @Table(name = "User")
	   @Entity
	   @Data
	   public static class User extends com.knits.jpa.orm.entities.common.User implements Serializable {

		  	@ManyToMany(mappedBy = "users")
			@Getter
			private List<Address> billingAddress= new ArrayList<>();

	   }
}



