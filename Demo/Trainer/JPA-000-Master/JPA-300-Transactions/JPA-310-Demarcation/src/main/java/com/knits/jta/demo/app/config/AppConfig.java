package com.knits.jta.demo.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.knits.jta.common.config.JpaContext;

@Configuration
@PropertySource("classpath:jta-310-demo.properties")
@Import({JpaContext.class})
@ComponentScan(basePackages = { "com.knits.jta.demo.app.controllers","com.knits.jta.demo.app.services" })
public class AppConfig {

	
	
}
