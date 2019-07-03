package com.knits.jpa.queries.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import lombok.extern.slf4j.Slf4j;

import com.knits.jpa.queries.model.Product;
import com.knits.jpa.queries.model.Software;
import com.knits.jpa.queries.model.Supplier;
import com.knits.jpa.queries.utils.JpaContextUtil;

@Slf4j
public class JpaQueryUtils {

	private static final String PU="JPA-410-JPQL-PU";
	private static EntityManagerFactory emf;
	
	
	public static void setup(){
		emf =JpaContextUtil.getEntityManagerFactory(PU);
		EntityManager em = emf.createEntityManager();
		insertTestData(em);		
	}
	
	public static EntityManager createNewEntityManager(){
		return  emf.createEntityManager();
	}
	
	public static <T> TypedQuery<T> createTypedQuery(String query, Class<T> entityClass){
		EntityManager em = emf.createEntityManager();
		return em.createQuery(query, entityClass);
	}
	
	public static Query createQuery(String jpql){
		EntityManager em = createNewEntityManager();
		return em.createQuery(jpql);
	}
	
	
	public static void logResultSet(List list){
		for (Object o : list){
			log.info(o.toString());
		}
	}
	
	
	private static void insertTestData(EntityManager em)  {

		 em.getTransaction().begin();
		 
        Supplier superCorp = new Supplier();
        superCorp.setName("SuperCorp");
        em.persist(superCorp);
        System.out.println("ID inserimento: "+superCorp.getId());
        
        Supplier megaInc = new Supplier();
        megaInc.setName("MegaInc");
        em.persist(megaInc); 
        System.out.println("ID inserimento: "+megaInc.getId());
        
        Product mouse = new Product("Mouse","Optical Wheel Mouse", 20.0);
        mouse.setSupplier(superCorp);
        superCorp.getProducts().add(mouse);
        
        Product mouse2 = new Product("Mouse","One Button Mouse", 22.0);
        mouse2.setSupplier(superCorp);
        superCorp.getProducts().add(mouse2);        
        
        Product keyboard = new Product("Keyboard", "101 Keys", 30.0);
        keyboard.setSupplier(megaInc);
        megaInc.getProducts().add(keyboard);

        Software webBrowser = new Software("Web Browser","Blocks Pop-ups", 75.0, "2.0");
        webBrowser.setSupplier(superCorp);
        superCorp.getProducts().add(webBrowser);

        Software email = new Software("Email","Blocks spam", 49.99, "4.1 RMX Edition");
        email.setSupplier(megaInc);
        megaInc.getProducts().add(email);
        
        em.getTransaction().commit();
        
    }
}
