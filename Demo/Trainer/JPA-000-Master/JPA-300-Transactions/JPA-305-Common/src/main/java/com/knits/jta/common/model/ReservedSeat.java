package com.knits.jta.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CD")
@Data
public class ReservedSeat extends AbstractEntity {
		
	@Column(name="placeNumber")
	private int placeNumber;
	
	@Column(name="documentNumber")
	private String documentNumber;

	@Column(name="category")
	private String category;	


}
