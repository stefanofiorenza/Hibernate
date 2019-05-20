package com.knits.jta.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContextUtil {

	
	public static void closeSilently(ApplicationContext context){
		 ((AnnotationConfigApplicationContext)context).close(); //avoid resource leaking 
	}
}
