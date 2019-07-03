package com.knits.jpa.queries.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Product extends AbstractEntity{
  
    public Product(String name, String description,
			double price) {
		super();
	
		this.name = name;
		this.description = description;
		this.price = price;
	}
    
	private Supplier supplier;    
    private String name;
    private String description;
    private double price;
    
    
}
