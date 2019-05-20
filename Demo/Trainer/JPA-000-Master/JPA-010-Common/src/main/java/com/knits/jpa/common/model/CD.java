package com.knits.jpa.common.model;

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
public class CD extends AbstractEntity {
		
	@Column(name="Artist")
	private String artist;
	
	@Column(name="Company")
	private String company;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Price")
	private double price;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Year")
	private int year;
	
	@Column(name="Quantity")
	private int quantity;
	
	
	@Override
	public String toString() {
		return 	"Artist: "+this.artist+"\n "+
			   	"Title: "+this.title+"\n"+
			   	"Price: "+this.price+"\n"+
			   	"Company: "+this.company+"\n"+
			   	"Country : "+this.country+"\n"+
			   	"Year: "+this.year+
			   	"Quantity: "+this.quantity;
	}

	


}
