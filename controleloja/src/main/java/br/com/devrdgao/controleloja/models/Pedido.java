package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private BigDecimal total;
	
	public Pedido(){
		super();
	}
	
	public Pedido(String codigoitem, String descricao, Integer quantidade, BigDecimal valoritem,
			BigDecimal precoitem, BigDecimal total) {
		
		this.codigoitem = codigoitem;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valoritem = valoritem;
		this.precoitem = precoitem;
		this.total = total;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	

}
