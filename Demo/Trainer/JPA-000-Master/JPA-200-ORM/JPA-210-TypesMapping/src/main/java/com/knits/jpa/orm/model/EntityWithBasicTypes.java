package com.knits.jpa.orm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.knits.jpa.common.model.AbstractEntity;

import lombok.Data;

@Entity
@Table(name = "BasicTypeTable")
@Data
public class EntityWithBasicTypes extends AbstractEntity{
	
	private int anIntegerField;
	private double anDoubleField;
	private String aVarCharField;
	private boolean aBooleanField;
		
}
