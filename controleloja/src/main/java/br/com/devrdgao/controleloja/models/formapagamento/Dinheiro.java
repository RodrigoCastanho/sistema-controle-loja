package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Dinheiro extends Pagamento {
	
	private String dinheiro;
	private BigDecimal valorrecebido;
	private BigDecimal valortroco;
	private BigDecimal valordinheiro;

	public Dinheiro() { 
		super(); 
	}
	
	public Dinheiro(String dinheiro, BigDecimal valorrecebido, BigDecimal valortroco, BigDecimal valordinheiro) {
		
		if(dinheiro.equals("Dinheiro")) {
			
			this.dinheiro = dinheiro;
			this.valorrecebido = valorrecebido;
			this.valortroco = valortroco;
			this.valordinheiro = valordinheiro;
		
						
		} else {
			
			this.dinheiro = "";
			this.valorrecebido = new BigDecimal("0.00");
			this.valortroco = new BigDecimal("0.00");
			this.valordinheiro = new BigDecimal("0.00");
		   
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

	public BigDecimal getValordinheiro() {
		return valordinheiro;
	}

	public void setValordinheiro(BigDecimal valordinheiro) {
		this.valordinheiro = valordinheiro;
	}
			
}
