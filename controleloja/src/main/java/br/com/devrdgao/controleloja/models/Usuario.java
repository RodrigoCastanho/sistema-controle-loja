package br.com.devrdgao.controleloja.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	
	@Id
	private Integer idusuario;
	private String login;
	private String nome;
	private String senha;
	
	@ManyToMany
	@JoinTable(name="usuario_acesso", 
	   joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idusuario"),
	   inverseJoinColumns = @JoinColumn(name = "id_acesso", referencedColumnName= "tipoAcesso"))	
	private Collection<RegrasAcesso> regrasAcesso;
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Collection<RegrasAcesso> getRegrasAcesso() {
		return regrasAcesso;
	}

	public void setRegrasAcesso(Collection<RegrasAcesso> regrasAcesso) {
		this.regrasAcesso = regrasAcesso;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.regrasAcesso;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
