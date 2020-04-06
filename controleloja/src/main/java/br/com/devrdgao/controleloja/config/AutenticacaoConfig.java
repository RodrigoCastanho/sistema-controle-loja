package br.com.devrdgao.controleloja.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.devrdgao.controleloja.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class AutenticacaoConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception  {
		 http.authorizeRequests()
		 		.anyRequest()
		 		.authenticated()
		    .and()
		       .exceptionHandling().accessDeniedPage("/index")
		    .and()         
		    .formLogin()
		    	.loginPage("/acessar")
		    	.permitAll()
		    .and()
		        .logout()
		        .logoutSuccessUrl("/acessar?logout")
		        .permitAll();			 
		 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userDetailsService)
           .passwordEncoder(new BCryptPasswordEncoder());
		   
		super.configure(auth);
	}
	
	
	
	
}
