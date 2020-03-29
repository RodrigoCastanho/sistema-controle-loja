package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class CupomNF {
	
	
	protected LocalDateTime data;
	protected String datacnf; 
	protected String valoritemcnf; 
	protected String precoitemcnf;
	protected String totalcnf;
	protected BigDecimal valortotal;
	protected String tipopagamentocnf;
	protected BigDecimal trococnf; 
	
	
	public String getDatacnf() {
		return datacnf;
	}
	public void setDatacnf(String datacnf) {
		this.datacnf = datacnf;
	}
	public String getValoritemcnf() {
		return valoritemcnf;
	}
	public void setValoritemcnf(String valoritemcnf) {
		this.valoritemcnf = valoritemcnf;
	}
	public String getPrecoitemcnf() {
		return precoitemcnf;
	}
	public void setPrecoitemcnf(String precoitemcnf) {
		this.precoitemcnf = precoitemcnf;
	}
	public String getTotalcnf() {
		return totalcnf;
	}
	public void setTotalcnf(String totalcnf) {
		this.totalcnf = totalcnf;
	}
	public String getTipopagamentocnf() {
		return tipopagamentocnf;
	}
	public void setTipopagamento(String tipopagamentocnf) {
		this.tipopagamentocnf = tipopagamentocnf;
	}
	public String getTrococnf() {
		return "";
	}
	
}
