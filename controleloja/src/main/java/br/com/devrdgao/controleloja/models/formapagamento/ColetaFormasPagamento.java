package br.com.devrdgao.controleloja.models.formapagamento;

import java.math.BigDecimal;

public class ColetaFormasPagamento {
	
	private BigDecimal valorrecebido;
	private BigDecimal troco;
	private Integer parcela;
	private BigDecimal valorparcela;
	private String dinheiro;
	private String debito;
	private String credito;

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
		

}
