package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

@Embeddable
public class Dinheiro extends Pagamento {
	
	private String dinheiro;
	private BigDecimal valorrecebido;
	private BigDecimal valortroco;

	public Dinheiro() { 
		super(); 
	}
	
	public Dinheiro(String dinheiro, BigDecimal valorrecebido, BigDecimal valortroco, 
					String desconto, BigDecimal totalpago) {
		
		if(dinheiro.equals("Dinheiro")) {
			
			this.dinheiro = dinheiro;
			this.valorrecebido = valorrecebido;
			this.valortroco = valortroco;
			desconto(desconto,totalpago);
			//super.totalpago = totalpago;
						
		} else {
			
			this.dinheiro = "";
			this.valorrecebido = new BigDecimal("0.00");
			this.valortroco = new BigDecimal("0.00");
			super.totalpago = new BigDecimal("0.00");
		   
		}
		
	}
		
	public BigDecimal getValorrecebido() {
		return valorrecebido;
	}
	public void setValorrecebido(BigDecimal valorrecebido) {
		this.valorrecebido = valorrecebido;
	}
	public BigDecimal getValortroco() {
		return valortroco;
	}
	public void setValortroco(BigDecimal valortroco) {
		this.valortroco = valortroco;
	}

	public String getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(String dinheiro) {
		this.dinheiro = dinheiro;
	}

	@Override
	protected BigDecimal desconto(String desconto, BigDecimal totalpago) {
		
	  if(desconto != "") {
		  
		  BigDecimal totaldesc = new BigDecimal("0.00");
          BigDecimal porcentagem = (new BigDecimal(desconto)).divide(new BigDecimal(100));	
          totaldesc = totalpago.multiply(porcentagem).setScale(2, RoundingMode.DOWN); 
		  return super.desconto = totaldesc;
	  }
	  return super.desconto = new BigDecimal("0.00");  
	  	
	}
		
}
