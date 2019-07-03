package com.knits.jta.common.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Qualification extends AbstractEntity{

	private String institution;
	private String qualificationType;
	private int grade;
}
