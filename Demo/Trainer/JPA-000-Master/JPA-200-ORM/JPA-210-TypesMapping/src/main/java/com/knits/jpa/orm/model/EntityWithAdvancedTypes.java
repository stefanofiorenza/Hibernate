package com.knits.jpa.orm.model;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.knits.jpa.common.model.AbstractEntity;

import lombok.Data;

@Entity
@Table(name = "AdvancedTypesTable")
@Data
public class EntityWithAdvancedTypes extends AbstractEntity{
	
	private WeekDayEnum giorno;
	
	@Lob
	private byte[] binary;
	
	@Lob
	private Blob blob;
	
	@Lob
	private Clob clob;
		
}
