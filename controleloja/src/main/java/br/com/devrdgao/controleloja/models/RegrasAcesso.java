package br.com.devrdgao.controleloja.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class RegrasAcesso implements GrantedAuthority {
	
	@Id
	private String tipoAcesso;
	
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.tipoAcesso;
	}

	public String getTipoAcesso() {
		return tipoAcesso;
	}

	public void setTipoAcesso(String tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}

}
