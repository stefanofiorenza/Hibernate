package com.knits.jpa.orm.entities.one.to.one.test;

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
import com.knits.jpa.orm.entities.common.MockEntities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestOneToOne02_FkColum extends AbstractJPAProgrammaticBootstrapTest {

	@Override
	protected Class<?>[] entities() {
		return new Class[] { Address.class, User.class };
	}
	
	@Test
	public void generateTables() {
		doInJPATransaction(entityManager -> {
			
			log.info("If hibernate.hbm2ddl.auto=create Or update tables will be created on bootstrap of EntityManagerFactory");
		});
	}

	@Test
	public void testInsertFirstTheParentEntity() {
		doInJPATransaction(entityManager -> {

			Address billingAddress = new Address();			
			MockEntities.fillData(billingAddress);
			
			User user = new User();
			MockEntities.fillData(user);
					
			user.setBillingAddress(billingAddress);
			entityManager.persist(user);
			
			entityManager.persist(billingAddress);
			

		});
	}
	
	
	@Test
	public void testCrudOnEntities() {
		
		doInJPATransaction(entityManager -> {

			Address billingAddress = new Address();			
			MockEntities.fillData(billingAddress);
			
			User user = new User();
			MockEntities.fillData(user);
			user.setBillingAddress(billingAddress);

			entityManager.persist(billingAddress); // address is parent of this relationship
			entityManager.persist(user);
			
			entityManager.remove(user);
			entityManager.remove(billingAddress); // address is parent of this relationship
		});
	}
	
	
	@Table(name = "User")
	@Entity	
	@Data
	private static class User extends com.knits.jpa.orm.entities.common.User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@OneToOne(mappedBy = "user")		
		private Address billingAddress;

	}

	@Table(name = "Address")
	@Entity
	@Data
	private static class Address extends com.knits.jpa.orm.entities.common.Address implements Serializable  {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		

		@OneToOne
		@JoinColumn(name = "user_id")
		private User user;

	}


}
