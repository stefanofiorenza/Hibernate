package com.knits.jpa.orm.entities.z.labs;

import lombok.Data;
import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;
import com.knits.jpa.orm.entities.common.MockEntities;

import javax.persistence.*;


public class LabTest extends AbstractJPAProgrammaticBootstrapTest{

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {    //...insert here your entities
	        		Address.class,
	        		User.class
	        };
	    }
	   
	   
	   @Test
	   public void testCrud() {
		   
		   
		   
		   doInJPATransaction(em -> {
				  
			   User user = new User();
			   Address address = new Address();
			   
			   MockEntities.fillData(address);
			   MockEntities.fillData(user);
			   
			   
			   em.persist(user);
			  
			  
			   
		   });		   
		  
	   }
	   
	   
	   //..define some entities as nested static classes
	   @Table(name = "User")
	   @Entity
	   @Data
	   static class User extends com.knits.jpa.orm.entities.common.User{

		   @Id
		   @GeneratedValue(strategy = GenerationType.AUTO)
		   private Long id;

		 //  private Address address;
	   }

		@Table(name = "Address")
		@Entity
		@Data
	   static class Address extends com.knits.jpa.orm.entities.common.Address{

			@Id
			@GeneratedValue(strategy = GenerationType.AUTO)
			private Long id;

		   	//private User user;
	   	
	   }
}


