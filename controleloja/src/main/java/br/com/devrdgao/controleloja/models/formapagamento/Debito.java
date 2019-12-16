package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Debito extends Pagamento{
	
	private String debito;
	
	public Debito() { 
		super();	
	}

	public Debito(String debito, BigDecimal desconto, BigDecimal totalpago) {
				
		if(debito.equals("debito")) {	
		
		  this.desconto = desconto;
	      this.totalpago = totalpago;
		  this.debito = debito;
		  
		}else {
		  	
		  this.desconto = new BigDecimal("0.00");
		  this.totalpago = new BigDecimal("0.00");
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
