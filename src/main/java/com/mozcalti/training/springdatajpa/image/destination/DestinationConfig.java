package com.mozcalti.training.springdatajpa.image.destination;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:database.properties")
@EnableJpaRepositories(basePackages = "com.mozcalti.training.springdatajpa.image.destination.repositories", entityManagerFactoryRef ="destinationEntityManager", transactionManagerRef="destinationTransactionManager")
public class DestinationConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean destinationEntityManager() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(destinationDataSource());
		emfb.setPackagesToScan("com.mozcalti.training.springdatajpa.image.destination.model");

		EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		emfb.setJpaVendorAdapter(vendorAdapter);
		emfb.setJpaDialect(eclipselinkDialect());
		Map<String, Object> properties = new HashMap<>();
		properties.put("eclipselink.weaving", "false");
		properties.put("eclipselink.ddl-generation", "create-or-extend-tables");

		emfb.setJpaPropertyMap(properties);

		return emfb;
	}

	@Bean
	public EclipseLinkJpaDialect eclipselinkDialect() {
		return new EclipseLinkJpaDialect();
	}

	@Bean
	public DataSource destinationDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("destination.jdbc.driver"));
		dataSource.setUrl(env.getProperty("destination.jdbc.url"));
		dataSource.setUsername(env.getProperty("destination.jdbc.username"));
		dataSource.setPassword(env.getProperty("destination.jdbc.password"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager destinationTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(destinationEntityManager().getObject());

		return transactionManager;
	}
}
