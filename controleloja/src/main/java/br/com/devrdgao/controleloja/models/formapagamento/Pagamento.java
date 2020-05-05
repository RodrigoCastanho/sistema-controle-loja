package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

public abstract class Pagamento {
	
	 protected BigDecimal desconto;
	 protected BigDecimal totalpago;
	 
	 
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getTotalpago() {
		return totalpago;
	}
	public void setTotalpago(BigDecimal totalpago) {
		this.totalpago = totalpago;
	}
		
}