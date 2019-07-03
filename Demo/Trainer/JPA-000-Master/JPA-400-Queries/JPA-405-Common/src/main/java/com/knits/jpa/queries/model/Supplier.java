package com.knits.jpa.queries.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Supplier extends AbstractEntity
{
    
	@Setter
    private String name;
    private List<Product> products = new ArrayList<>();
    
}
