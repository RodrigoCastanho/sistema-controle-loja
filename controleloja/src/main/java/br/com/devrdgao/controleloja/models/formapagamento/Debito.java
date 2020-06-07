package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Debito extends Pagamento{
	
	private String debito;
	
	public Debito() { 
		super();	
	}

	public Debito(String debito, BigDecimal totalpago) {
				
		if(debito.equals("Débito")) {	
			
		  this.debito = debito;
		  
		} else {
		  	
		  super.totalpago = new BigDecimal("0.00");
		  this.debito = "";	
			
		} 
	} 

	public String getDebito() { 
		return debito;
	} 
	public void setDebito(String debito) {
		this.debito = debito;
	}
	 	
}
