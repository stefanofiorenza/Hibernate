package com.knits.jta.concurrency.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.knits.jta.common.model.ReservedSeat;
import com.knits.jta.common.model.User;


@Service
@Slf4j
public class SeatReservationService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertReservationData(User user){
		log.info("Saved {} ",user.toString());
	}

	public void executePayment(){
		log.info("Catpure reservation payment forwarding to Credit Card system ");		
	}
	
	
	public void reserveSeat(ReservedSeat reservedSeat){
		log.info("Seat reserved. Confirmation ",reservedSeat.toString());		
	}
}
