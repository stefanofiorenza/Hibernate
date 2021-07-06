package com.knits.jpa.orm.entities01.one.to.one.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;
import com.knits.jpa.orm.entities.common.MockEntities;
import com.knits.jpa.orm.entities01.one.to.one.test.TestOneToOne01_SharedPrimaryKey.Address;
import com.knits.jpa.orm.entities01.one.to.one.test.TestOneToOne01_SharedPrimaryKey.User;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestOneToOne02_FkColum extends AbstractJPAProgrammaticBootstrapTest {

	@Test
	public void generateTables() {
		doInJPATransaction(entityManager -> {			
			log.info("If hibernate.hbm2ddl.auto=create Or update tables will be created on bootstrap of EntityManagerFactory");
		});
	}


	
	
	@Test
	public void testInsertFirstTheParentEntity() {
		
		doInJPATransaction(entityManager -> {

			//1) connecting only parent to child will result in user_id= null
			//user.setBillingAddress(address);
			
			//2) This will work: connect child to parent 
			address.setUser(user);
			
					
			entityManager.persist(user);
			entityManager.persist(address); 
			
		});
	}

	
	

	
	@Table(name = "User")
	@Entity	
	@Data
	private static class User extends com.knits.jpa.orm.entities.common.User {

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

	
	private Address address;
	private User user;

	@Override
	protected Class<?>[] entities() {
		return new Class[] { Address.class, User.class };
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

			// @Review (1): If entity was persisted has id. In that case load it from DB and
			// delete it.

			if (user.getId() != null) {
				User userInDB = entityManager.find(User.class, user.getId());
				if (userInDB != null) {
					entityManager.remove(userInDB);
				}
			}

			if (address.getId() != null) {
				Address addressInDb = entityManager.find(Address.class, address.getId());
				if (addressInDb != null) {
					entityManager.remove(addressInDb);
				}
			}
		});
	}

}
