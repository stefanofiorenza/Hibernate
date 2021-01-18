package com.knits.jpa.orm.entities.one.to.one.test;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;
import com.knits.jpa.orm.entities.common.MockEntities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestOneToOne01_SharedPrimaryKey extends AbstractJPAProgrammaticBootstrapTest{

		private Address address;
		private User user;

	   @Override
	    protected Class<?>[] entities() {
	        return new Class[] {
	        		Address.class,
	        		User.class
	        };
	    }


	   @Before
	   public void init() {
		   super.init();
		   address = new Address();
		   user = new User();
		   MockEntities.fillData(address);
		   MockEntities.fillData(user);
	   }

		@After
		public void cleanup() {
			doInJPATransaction(entityManager -> {
				log.info("Remove last persisted entities");

				// @Review (1): If entity was persisted has id. In that case load it from DB and delete it.

				if (user.getId()!=null){
					User userInDB= entityManager.find(User.class,user.getId());
					if(userInDB!=null){
						entityManager.remove(userInDB);
					}
				}

				if (address.getId()!=null){
					Address addressInDb = entityManager.find(Address.class,address.getId());
					if(addressInDb!=null){
						entityManager.remove(addressInDb);
					}
				}
			});
		}

	   @Test
	   public void testTableCreations() {
		   doInJPATransaction(entityManager -> {
			   log.info("Check tables created in DB");
		   });
	   }
	   
	  	   
	   
	   @Test
	   public void testBidirectional() {
		   
		   doInJPATransaction(entityManager ->{

			  // @Review (1): Need to update BOTH side of relationship
				   
			   /*
			   	user.setAddress(address);
			   	address.setUser(user);
				*/
	   
			   //@Review (2):Instead of replication above code (@Review(1)) everywhere in services..
			   /*
					   //TC1) only one set is updating both objects
						   user.setAddress(address);			   
						   Assert.assertNotNull(address.getUser());
						   
					   //TC2) can remove association on both sides with null
						   user.setAddress(null);
						   Assert.assertNull(address.getUser());
					   
					   //TC3)Careful!! address.setUser will not update both sides!			   
						   address.setUser(user);
						   Assert.assertNull(user.getAddress());
			    */ 
		   
			   	entityManager.persist(user);
		   });
	   }
	   
	   
	   @Test
	   public void testCascade() {
		   
		   doInJPATransaction(entityManager ->{
			   
			   //TC4) Cascade PERSIST or ALL
			   testWithCascadePersistEnabled(entityManager);			   
			   
		   });		   
	   }


	   
	   
	   
	private void testWithCascadePersistEnabled(EntityManager entityManager) {
		
		   //both transient in beginning (no ids)
		   Assert.assertNull(user.getId());
		   Assert.assertNull(address.getId());
		   
		   user.setAddress(address);		  
		   entityManager.persist(user);
		   
		 //both saved in DB with entityManager.persist(parentEntity)
		   Assert.assertNotNull(user.getId());
		   Assert.assertNotNull(address.getId());		   
	}
	   
	   
	   

	   
	   
	   
	   
	   @Table(name = "User")
	   @Entity
	   @Data
	   static class User extends com.knits.jpa.orm.entities.common.User implements Serializable {
	   
		   		
		    @Id
		 	@GeneratedValue(strategy = GenerationType.AUTO)
		    private Long id;
		     
		    //@Review (3): cascade policy = propagate action to child entity
		   	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)		 
		   	private Address address;

		   	
		   	//@Review (2):Better to handle bidirectional association in parent class	
		   	
		   	public void setAddress(Address address) {
		   		
		   		if(address!=null) {
		   			address.setUser(this);		   				   					   				
		   		}else if (this.address!=null){
		   			this.address.setUser(null);	//remove both side of association in case of address=null
		   		}
		   		
		   		this.address=address;		   		
		   	}
	   }
	   
	   
	   @Table(name = "Address")
	   @Entity
	   @Data
	   static class Address extends com.knits.jpa.orm.entities.common.Address implements Serializable{ 

		   	@Id		   
		   	private Long id;
		 
		   	
		   	@OneToOne(fetch = FetchType.LAZY)
			@MapsId
			@JoinColumn(name = "id")			
		   	private User user;

		   
		 
	   }
	   
}





