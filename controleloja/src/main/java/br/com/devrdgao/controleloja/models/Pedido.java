package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long codigopedido;
	
  	private String codigoitem;
  	private String descricao;
  	private Integer quantidade;
  	private BigDecimal valoritem;
	private BigDecimal precoitem;
	private BigDecimal valortotal;
	@Transient
	private LocalDateTime data; 
	@Transient 
	private String valoritemcv;
	@Transient 
	private String precoitemcv;
	@Transient 
	private String total;
	
	public Pedido() {
		super();
	}
	
	public Pedido(String codigoitem, String descricao, Integer quantidade, BigDecimal valoritem, BigDecimal precoitem, 
			      LocalDateTime data, BigDecimal valortotal) {
		
		this.codigoitem = codigoitem;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valoritem = valoritem;
		this.precoitem = precoitem;
		this.data = data;
		this.valortotal = valortotal;
				
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

	public String getData() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");		
		return data.format(format);
	}
	
	public String getPrecoitemcv() {
		DecimalFormat df = new DecimalFormat("#,##0.00");				
		return precoitemcv = df.format(this.precoitem);
	}
	
	public String getValoritemcv() {
		DecimalFormat df = new DecimalFormat("#,##0.00");				     
		return valoritemcv = df.format(this.valoritem);
	}

	public String getTotal() {
		DecimalFormat df = new DecimalFormat("#,##0.00");    
		return total = df.format(this.valortotal);
	}	
	
}
