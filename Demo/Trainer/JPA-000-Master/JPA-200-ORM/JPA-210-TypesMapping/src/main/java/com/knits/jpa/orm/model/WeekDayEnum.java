package com.knits.jpa.orm.model;

import lombok.Getter;
import lombok.Setter;

public enum WeekDayEnum {

	Lunedi(1),
	Martedi(2) ,
	Mercoledi(3),
	Giovedi(4),
	Venerdi(5),
	Sabato(6),
	Domenica(7);
	
	@Getter
	@Setter
	private int giornoNumero;
	
	private WeekDayEnum(int giorno){
		this.giornoNumero=giorno;
	}


	
	
	
}
