package com.knits.jpa.common.utils;

import javax.persistence.EntityManager;

import lombok.Getter;
import lombok.Setter;

import com.knits.jpa.common.model.AbstractEntity;

public class BasicDao<T extends AbstractEntity> {

	@Getter
	@Setter
	private EntityManager em;
	
		
	public T findById(Class<T> entityClass,Long primaryKey){
		return em.find(entityClass,primaryKey);
	}
	
	public Long save(T entity){
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity.getId();
	}
	
	public void update(T entity){
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}
	
	public void delete(T entity){
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();	
	}
	
	
}
