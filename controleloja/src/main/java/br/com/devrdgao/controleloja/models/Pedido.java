package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.devrdgao.controleloja.models.formapagamento.ColetaFormasPagamento;
import br.com.devrdgao.controleloja.models.formapagamento.Credito;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.models.formapagamento.Dinheiro;

@Entity
public class Pedido extends CupomNF {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long codigopedido;
	
  	private String codigoitem;
  	private String descricao;
  	private Integer quantidade;
  	private BigDecimal valoritem;
	private BigDecimal precoitem;
	
	public Pedido() {
		super();
	}
	
	public Pedido(String codigoitem, String descricao, Integer quantidade, BigDecimal valoritem, BigDecimal precoitem, 
			      LocalDateTime data, BigDecimal valortotal, ColetaFormasPagamento pagamento) {
		
		this.codigoitem = codigoitem;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valoritem = valoritem;
		this.precoitem = precoitem;
		super.data = data;
		super.valortotal = valortotal;
		super.tipopagamentocnf = pagamento.getDinheiro()
							    .concat(pagamento.getDebito())
							    .concat(pagamento.getCredito()); 
		super.trococnf = pagamento.getTroco();
		
	}
		
	public Long getCodigopedido() {
		return codigopedido;
	}
	public void setCodigopedido(Long codigopedido) {
		this.codigopedido = codigopedido;
	}
	public String getCodigoitem() {
		return codigoitem;
	}
	public void setCodigoitem(String codigoitem) {
		this.codigoitem = codigoitem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValoritem() {
		return valoritem;
	}
	public void setValoritem(BigDecimal valoritem) {
		this.valoritem = valoritem;
	}
	public BigDecimal getPrecoitem() {
		return precoitem;
	}
	public void setPrecoitem(BigDecimal precoitem) {
		this.precoitem = precoitem;
	}
	
	@Override
	public String getDatacnf() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return super.datacnf = this.data.format(format);
	}
	
	@Override
	public String getPrecoitemcnf() {
		DecimalFormat df = new DecimalFormat("#,##0.00");	
		return super.precoitemcnf = df.format(this.precoitem);
	}
	
	@Override
	public String getValoritemcnf() {
		DecimalFormat df = new DecimalFormat("#,##0.00");	
		return super.valoritemcnf = df.format(this.valoritem);
	} 
	
	@Override
	public String getTotalcnf() {
		DecimalFormat df = new DecimalFormat("#,##0.00"); 
		return super.totalcnf = df.format(super.valortotal);
	}	
	
	@Override
	public String getTipopagamentocnf() {
		return super.tipopagamentocnf;
	}
	
	@Override
	public String getTrococnf() {
		DecimalFormat df = new DecimalFormat("#,##0.00"); 
		return df.format(super.trococnf);
	}
		
}
