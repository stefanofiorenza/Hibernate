package test.orm.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JpaContextUtil {

	private static EntityManagerFactory emFactory;
	
	
	public static EntityManagerFactory getEntityManagerFactory(String persistenceUnitName){
		if(emFactory==null){
			emFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return emFactory;
	}
	

	
	public static void close(){
		emFactory.close();
	}

	


	public static void doInJPATransaction(JPATransactionVoidFunction function) {
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {			
			
			entityManager = emFactory.createEntityManager();			
			txn = entityManager.getTransaction();
			txn.begin();
			function.accept(entityManager);
			if (!txn.getRollbackOnly()) {
				txn.commit();
			} else {
				try {
					txn.rollback();
				} catch (Exception e) {
					log.error("Rollback failure", e);
				}
			}
		} catch (Throwable t) {
			if (txn != null && txn.isActive()) {
				try {
					txn.rollback();
				} catch (Exception e) {
					log.error("Rollback failure", e);
				}
			}
			throw t;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
