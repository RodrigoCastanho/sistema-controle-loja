package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Item {
	
	@Id
	@NotBlank(message="Insira um Código")
	private String codigoitem;
	
	@NotBlank(message="Insira uma Descrição")
	private String descricao;
	
	private BigDecimal precovenda;
	private BigDecimal valoritem;
	
	@NotNull(message="Insira quantidade de itens?")
	private Integer quantidade;
	
	@NotNull(message="Insira quantidade de itens em estoque")
	private Integer quantminima;
	
	@NotNull(message="Escolha categoria do item")
	private String categoria;
		
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public BigDecimal getPrecovenda() {
		return precovenda;
	}
	
	public void setPrecovenda(BigDecimal precovenda) {
		this.precovenda = precovenda;
	}
	
	public BigDecimal getValoritem() {
		return valoritem;
	}
	
	public void setValoritem(BigDecimal valoritem) {
		this.valoritem = valoritem;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantminima() {
		return quantminima;
	}
	
	public void setQuantminima(Integer quantminima) {
		this.quantminima = quantminima;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	

}
