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
public class CaixaAbertura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigocaixa;
	private LocalDate  dataabertura;
	private BigDecimal valorabertura;
	private BigDecimal valorfechamento;
	
	@ManyToOne
	@JoinColumn(name = "usuario_login")
	private Usuario usuario;
	
	
	public void calculoValorFechamento(BigDecimal valor) {	
		this.valorfechamento = valorfechamento.subtract(valor);			
	}
	
	public Integer getCodigocaixa() {
		return codigocaixa;
	}
	public void setCodigocaixa(Integer codigocaixa) {
		this.codigocaixa = codigocaixa;
	}
	public LocalDate getDataabertura() {
		return dataabertura;
	}
	public void setDataabertura(LocalDate dataabertura) {
		this.dataabertura = dataabertura;
	}
	public BigDecimal getValorabertura() {
		return valorabertura;
	}
	public void setValorabertura(BigDecimal valorabertura) {
		this.valorabertura = valorabertura;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public BigDecimal getValorfechamento() {
		return valorfechamento;
	}
	public void setValorfechamento(BigDecimal valorfechamento) {
		this.valorfechamento = valorfechamento;
	} 
	
	
	
	
}
 