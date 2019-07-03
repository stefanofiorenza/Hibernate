package com.knits.jpa.queries.model;

import javax.persistence.Entity;

import lombok.Data;


@Entity
@Data
public class Software extends Product
{
    public Software(String name, String description, double price, String version) {
		super(name, description, price);
		this.version=version;
	}

	private String version;      
}
