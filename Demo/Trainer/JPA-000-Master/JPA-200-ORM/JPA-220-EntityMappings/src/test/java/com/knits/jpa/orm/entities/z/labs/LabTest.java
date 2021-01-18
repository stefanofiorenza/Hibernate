package com.knits.jpa.orm.entities.z.labs;

import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;
import com.knits.jpa.orm.entities.common.MockEntities;


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
	   static class User extends com.knits.jpa.orm.entities.common.User{
			
		   
		   private Address address;
	   }

	   static class Address extends com.knits.jpa.orm.entities.common.Address{
		   
		   private User user;
	   	
	   }
}


