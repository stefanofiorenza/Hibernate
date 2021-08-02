package test.orm.mappings.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.junit.Test;

import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.Getter;
import test.orm.core.AbstractJPAProgrammaticBootstrapTest;


public class TestRelationships extends AbstractJPAProgrammaticBootstrapTest{

	@Override
	protected Class<?>[] entities() {
		 return new Class[] {
	        		Address.class,
	        		User.class
	        };
	}

	
	   @Table(name = "Address")
	   @Entity
	   @Data
	   public static class Address  implements Serializable{ 

		   	@Id
		   	@GeneratedValue(strategy = GenerationType.AUTO)
		   	private Long id;

		   
			private String fullAddress;
		   	
			
//			@OneToOne(cascade = CascadeType.ALL)
//			@JoinTable(name = "user_address_one_2_one",
//					joinColumns = @JoinColumn(name = "address_id"),
//					inverseJoinColumns = @JoinColumn(name = "user_id")
//			)
			
			@OneToOne(cascade = CascadeType.ALL)
			@JoinColumn(name = "user_id")
			@Getter
			private User user;
			
//			@OneToMany(cascade = CascadeType.ALL)
//			@JoinTable(name = "user_address_one_many",
//					joinColumns = @JoinColumn(name = "address_id"),
//					inverseJoinColumns = @JoinColumn(name = "user_id")
//			)
//			@Getter
//			private List<User> users= new ArrayList<>();
		   	
		   	
//			@ManyToMany(cascade = CascadeType.ALL)
//			@JoinTable(name = "user_address",
//					joinColumns = @JoinColumn(name = "address_id"),
//					inverseJoinColumns = @JoinColumn(name = "user_id")
//			)
//			@Getter
//			private List<User> users= new ArrayList<>();
	   }


	   @Table(name = "User")
	   @Entity
	   @Data
	   public static class User implements Serializable {

		   	@Id
		   	@GeneratedValue(strategy = GenerationType.AUTO)
		   	private Long id;
		   	
		   
			private String fullName;	
			
//		  	@ManyToMany(mappedBy = "users")
//			@Getter
//			private List<Address> billingAddress= new ArrayList<>();

	   }
	   
	   
	   
	   @Test
	   public void testCrud() {
		   doInJPATransaction(entityManager -> {
			  
			   Address billingAddress = new Address();
			   billingAddress.setFullAddress("Via Giotto 9 Roma");
			   
			   User user = new User();
			   user.setFullName("Stefano Fiorenza");
			   
			   
			   entityManager.persist(billingAddress);
			   entityManager.persist(user);
		   });
	   }
}
