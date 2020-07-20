package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Debito extends Pagamento {
	
	private String debito;
	private BigDecimal valordebito;
	
	public Debito() { 
		super();	
	}

	public Debito(String debito, BigDecimal valordebito) {
				
		if(debito.equals("Débito")) {	
			
		  this.debito = debito;
		  this.valordebito = valordebito;
		  
		} else {
		  	
		  this.debito = "";
		  this.valordebito = new BigDecimal("0.00");
			
		} 
	} 

	public String getDebito() { 
		return debito;
	} 
	public void setDebito(String debito) {
		this.debito = debito;
	}

	public BigDecimal getValordebito() {
		return valordebito;
	}

	public void setValordebito(BigDecimal valordebito) {
		this.valordebito = valordebito;
	}
	     	
}
