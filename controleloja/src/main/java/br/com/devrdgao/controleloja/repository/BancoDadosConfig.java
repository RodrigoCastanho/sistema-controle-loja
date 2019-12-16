package br.com.devrdgao.controleloja.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class BancoDadosConfig {
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
	    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://localhost:3306/lojafran?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("root");
		ds.setPassword("test");
		return ds;		
		
	}
	/*@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://localhost:3306/lojafran");
		ds.setUsername("root");
		ds.setPassword("test");
		return ds;		
		
	}*/
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		 HibernateJpaVendorAdapter ad = new HibernateJpaVendorAdapter();
		 ad.setDatabase(Database.MYSQL);
		 ad.setShowSql(true);
		 ad.setGenerateDdl(true);
		 ad.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
		 ad.setPrepareConnection(true);
		 return ad;
	}
		
}
