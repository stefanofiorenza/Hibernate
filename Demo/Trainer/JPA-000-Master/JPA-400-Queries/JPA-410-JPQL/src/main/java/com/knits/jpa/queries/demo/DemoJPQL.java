package com.knits.jpa.queries.demo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.knits.jpa.queries.model.Product;

public class DemoJPQL {

	public static void main(String[] args) {
		JpaQueryUtils.setup();
		demo01JPQL_AllProducts();
		
	}

	private static void demo01JPQL_AllProducts() {
		Query query = JpaQueryUtils.createQuery("from Product");		
		JpaQueryUtils.logResultSet(query.getResultList());
	}

	private static void demo02JPQL_AllProductsTypedQuery() {		
		TypedQuery<Product> queryforProducts = JpaQueryUtils.createTypedQuery("from Product", Product.class);
		JpaQueryUtils.logResultSet(queryforProducts.getResultList());
	}
	
	

	
}
