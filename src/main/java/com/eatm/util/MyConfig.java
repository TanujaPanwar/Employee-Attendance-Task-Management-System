package com.eatm.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.eatm")
public class MyConfig {
	@Bean
	public EntityManagerFactory getEMF()
	{
		return Persistence.createEntityManagerFactory("vikas");
	}
	@Bean
	public EntityManager getEM() {
		return getEMF().createEntityManager();
		
	}
	@Bean
	public EntityTransaction getET() {
		return getEM().getTransaction();
		
	}
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
