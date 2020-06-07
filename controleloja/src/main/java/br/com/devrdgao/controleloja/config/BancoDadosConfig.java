package br.com.devrdgao.controleloja.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class BancoDadosConfig {
	
	@Bean
	public HikariDataSource dataSource() {
		
		HikariConfig hk = new HikariConfig();
		hk.setJdbcUrl("jdbc:mysql://localhost:3306/lojafran?useTimezone=true&serverTimezone=America/Sao_Paulo");
		hk.setUsername("root");
		hk.setPassword("test");
		hk.addDataSourceProperty("cachePrepStmts", "true");
		hk.addDataSourceProperty("prepStmtCacheSize", "250");
		hk.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
				
        return new HikariDataSource(hk);			
	}
	
	
//	@Bean
//	public DataSource dataSource() {
//		
//		
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//	    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	    ds.setUrl("jdbc:mysql://localhost:3306/lojafran?useTimezone=true&serverTimezone=America/Sao_Paulo");
//		ds.setUsername("root");
//		ds.setPassword("test");
//		return ds;		
//		
//	}

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
