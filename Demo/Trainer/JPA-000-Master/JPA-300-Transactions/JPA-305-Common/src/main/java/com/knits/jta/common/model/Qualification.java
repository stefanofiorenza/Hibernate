package com.knits.jta.common.model;

import lombok.Data;

@Data
public class Qualification extends AbstractEntity{

	private String institution;
	private String qualificationType;
	private int grade;
}
