package com.knits.jta.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jta-300-transactions.properties")
public class JpaContext {

	
	@Value("${datasource.url}")
	private String url;
	
	@Value("${datasource.username}")
	private String username;
	
	@Value("${datasource.password}")
	private String password;
	
	@Value("${datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${jpa.sqldialect}")
	private String sqlDialect;
	
	@Value("${jpa.autoddl}")
	private String autoDdl;
	
	@Value("${jpa.pu.name}")
	private String persistenceUnitName;
	
	@Value("${jta.timeout}")
	private int jtaTransactionTimeout;
	
	@Bean
	public DataSource jtaDatasource(){		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);		
		return dataSource;
	}
	
	private HibernateJpaVendorAdapter hibernateJpaAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setDatabasePlatform(sqlDialect);
		return adapter;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean localJpaContainer(){		
		LocalContainerEntityManagerFactoryBean jpaContainer= new LocalContainerEntityManagerFactoryBean();
		jpaContainer.setPersistenceUnitName(persistenceUnitName);
		jpaContainer.setDataSource(jtaDatasource());
		jpaContainer.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", autoDdl);
		jpaContainer.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());	
		jpaContainer.setJpaVendorAdapter(hibernateJpaAdapter());
		return jpaContainer;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager= new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(localJpaContainer().getObject());
		transactionManager.setDefaultTimeout(jtaTransactionTimeout);
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}
	

}
