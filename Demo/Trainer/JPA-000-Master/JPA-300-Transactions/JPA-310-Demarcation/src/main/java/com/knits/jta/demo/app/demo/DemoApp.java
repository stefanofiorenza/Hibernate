package com.knits.jta.demo.app.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.knits.jta.common.beans.ResponseBean;
import com.knits.jta.demo.app.config.AppConfig;
import com.knits.jta.demo.app.controllers.RegistrationController;

@Slf4j
public class DemoApp {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		  //1) MVC Framework maps HTTP request to Controller.method...
		  RegistrationController controller =context.getBean(RegistrationController.class);
		  ResponseBean<Integer> response=controller.registerUser();
		  
		  
		  //2) MVC Framework maps  ResponseBean<Integer> to Http Response...
		  log.info("Response sent to App Client: {}",response);
		  
		  
		
		  ((AnnotationConfigApplicationContext)context).close(); //avoid resource leaking 
		  
	}

}
