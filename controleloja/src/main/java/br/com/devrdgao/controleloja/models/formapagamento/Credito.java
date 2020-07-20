package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Credito extends Pagamento {
	
	private String credito;
	private Integer parcelas;
	private BigDecimal valorparcelado;
	private BigDecimal valorcredito;
	
	public Credito() {
		super();
	}
	
	public Credito(String credito, Integer parcelas, BigDecimal valorparcelado, BigDecimal valorcredito) {
				
	  if(credito.equals("Crédito")) {
		    
		  this.credito = credito; 
		  this.valorparcelado = valorparcelado;	
		  this.parcelas = parcelas;
		  this.valorcredito = valorcredito;
 
	  }else {
		  
		  this.credito = "";
		  this.valorparcelado = new BigDecimal("0.00");	
		  this.parcelas = 0;
		  this.valorcredito = new BigDecimal("0.00");	
	  
	  }
					
	}
	
	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public BigDecimal getValorparcelado() {
		return valorparcelado;
	}

	public void setValorparcelado(BigDecimal valorparcelado) {
		this.valorparcelado = valorparcelado;
	}

	public String getCredito() {
		return credito;
	}

	public void setCredito(String credito) {
		this.credito = credito;
	}

	public BigDecimal getValorcredito() {
		return valorcredito;
	}

	public void setValorcredito(BigDecimal valorcredito) {
		this.valorcredito = valorcredito;
	}
	
}
