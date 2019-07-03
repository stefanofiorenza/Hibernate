package com.knits.jta.concurrency.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.knits.jta.common.config.JpaContext;

@Configuration
//@PropertySource("classpath:jta-320-demo.properties")
@Import({JpaContext.class})
@ComponentScan(basePackages = { "com.knits.jta.concurrency.services" })
public class DemoConfig {

	
	
}
