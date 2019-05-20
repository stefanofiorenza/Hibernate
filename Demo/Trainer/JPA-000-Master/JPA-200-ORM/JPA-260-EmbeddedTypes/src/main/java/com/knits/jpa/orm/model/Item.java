package com.knits.jpa.orm.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import com.knits.jpa.common.model.AbstractEntity;

public class Item extends AbstractEntity {

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	private Set images = new HashSet();
	



}
