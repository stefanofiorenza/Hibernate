package com.knits.jta.concurrency.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.knits.jta.common.utils.AppContextUtil;
import com.knits.jta.common.utils.Mocks;
import com.knits.jta.concurrency.config.DemoConfig;
import com.knits.jta.concurrency.services.SeatReservationService;

public class DemoConcurrentTransactions {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		
		
		//1) Every user request is processed in a new thread 
		threadPool.submit(new Runnable() {			
			@Override
			public void run() {
				SeatReservationService seatReservationService =context.getBean(SeatReservationService.class);
				seatReservationService.insertReservationData(Mocks.mockUser());
				seatReservationService.executePayment();
				seatReservationService.reserveSeat(Mocks.mockReservedSeat());
			}
		});

		
		 AppContextUtil.closeSilently(context);
	}

}
