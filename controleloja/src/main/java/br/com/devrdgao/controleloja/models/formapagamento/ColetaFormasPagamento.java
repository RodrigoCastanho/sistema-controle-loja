package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

public class ColetaFormasPagamento {
	
	private BigDecimal valorrecebido;
	private BigDecimal troco;
	private Integer parcela;
	private BigDecimal valorparcela;
	private BigDecimal valorcredito;
	private BigDecimal valordinheiro;
	private BigDecimal valordebito;
	private String dinheiro;
	private String debito;
	private String credito;
	private String desconto;

	public BigDecimal getValorrecebido() {
		return valorrecebido;
	}
	public void setValorrecebido(BigDecimal valorrecebido) {
		this.valorrecebido = valorrecebido;
	}	
	public BigDecimal getTroco() {
		return troco;
	}
	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	public BigDecimal getValorparcela() {
		return valorparcela;
	}
	public void setValorparcela(BigDecimal valorparcela) {
		this.valorparcela = valorparcela;
	}
	public BigDecimal getValorcredito() {
		return valorcredito;
	}
	public void setValorcredito(BigDecimal valorcredito) {
		this.valorcredito = valorcredito;
	}
	
	public BigDecimal getValordinheiro() {
		return valordinheiro;
	}
	public void setValordinheiro(BigDecimal valordinheiro) {
		this.valordinheiro = valordinheiro;
	}
	public BigDecimal getValordebito() {
		return valordebito;
	}
	public void setValordebito(BigDecimal valordebito) {
		this.valordebito = valordebito;
	}
	public String getDebito() {
		return debito;
	}
	public void setDebito(String debito) {
		this.debito = debito;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public String getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(String dinheiro) {
		this.dinheiro = dinheiro;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}	
}
