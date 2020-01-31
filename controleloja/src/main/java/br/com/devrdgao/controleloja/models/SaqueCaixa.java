package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SaqueCaixa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigosqcaixa;
	private LocalDate datasaque;
	private BigDecimal valorretirado;
	private String justificativa;
	
	@ManyToOne
	@JoinColumn(name = "usuario_login")
	private Usuario usuario;
		
	public Integer getCodigosqcaixa() {
		return codigosqcaixa;
	}

	public void setCodigosqcaixa(Integer codigosqcaixa) {
		this.codigosqcaixa = codigosqcaixa;
	}

	public LocalDate getDatasaque() {
		return datasaque;
	}

	public void setDatasaque(LocalDate datasaque) {
		this.datasaque = datasaque;
	}

	public BigDecimal getValorretirado() {
		return valorretirado;
	}

	public void setValorretirado(BigDecimal valorretirado) {
		this.valorretirado = valorretirado;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
