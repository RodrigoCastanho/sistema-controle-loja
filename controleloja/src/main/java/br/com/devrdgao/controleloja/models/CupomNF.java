package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class CupomNF {
	
	
	//protected LocalDateTime data;
	protected LocalDateTime datacnf; 
	protected String valoritemcnf; 
	protected String precoitemcnf;
	protected BigDecimal totalcnf;
	protected String tipopagamentocnf;
	protected BigDecimal trococnf; 
	protected Long codigovendacnf;
	
	
	public String getDatacnf() {
		return "";
	}
	
	public void setDatacnf(LocalDateTime datacnf) {
		this.datacnf = datacnf;
	}


	public String getValoritemcnf() {
		return valoritemcnf;
	}

	public String getPrecoitemcnf() {
		return precoitemcnf;
	}

	public String getTotalcnf() {
		return "";
	}
	
	public void setTotalcnf(BigDecimal totalcnf) {
		this.totalcnf = totalcnf;
	}

	public String getTipopagamentocnf() {
		return tipopagamentocnf;
	}
	
	public void setTipopagamentocnf(String tipopagamentocnf) {
		this.tipopagamentocnf = tipopagamentocnf;
	}

	public String getTrococnf() {
		return "";
	}

	public void setTrococnf(BigDecimal trococnf) {
		this.trococnf = trococnf;
	}

	public Long getCodigovendacnf() {
		return codigovendacnf;
	}

	public void setCodigovendacnf(Long codigovendacnf) {
		this.codigovendacnf = codigovendacnf;
	}
	   	
}
