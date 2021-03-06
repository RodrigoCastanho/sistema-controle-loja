package br.com.devrdgao.controleloja.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.devrdgao.controleloja.models.formapagamento.Credito;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.models.formapagamento.Dinheiro;

@Entity
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigovenda;
	
	private LocalDateTime data;
	@Embedded
	private Dinheiro formpagdinheiro;
	@Embedded
	private Debito formpagdebito;
	@Embedded
	private Credito formpagcredito;
	
	private BigDecimal desconto;
    private BigDecimal valorvenda;
    
    @ManyToOne
	@JoinColumn(name = "usuario_login")
	private Usuario usuario;
    
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name="venda_pedido", 
			   joinColumns = @JoinColumn(name = "cod_venda"),
			   inverseJoinColumns = @JoinColumn(name = "cod_pedido"))
    
	private List<Pedido> pedidos;
	
	public Venda() { 
		super();
	}

	public Venda(LocalDateTime data, Dinheiro formpagdinheiro, Debito formpagdebito, Credito formpagcredito,
			BigDecimal desconto, BigDecimal valorvenda, List<Pedido> pedidos, Usuario usuario) {
		
		this.data = data;
		this.formpagdinheiro = formpagdinheiro;
		this.formpagdebito = formpagdebito;
		this.formpagcredito = formpagcredito;
		this.desconto = desconto;
		this.valorvenda = validadorPGCreditoValorTotal(formpagcredito, formpagdinheiro, formpagdebito, valorvenda); //define valor da venda
		this.pedidos = pedidos;
		this.usuario = usuario;
		
	}

	public Long getCodigovenda() {
		return codigovenda;
	}

	public void setCodigovenda(Long codigovenda) {
		this.codigovenda = codigovenda;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public BigDecimal getValorvenda() {
		return valorvenda;
	}

	public void setValorvenda(BigDecimal valorvenda) {
		this.valorvenda = valorvenda;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Dinheiro getFormpagdinheiro() {
		return formpagdinheiro;
	}

	public void setFormpagdinheiro(Dinheiro formpagdinheiro) {
		this.formpagdinheiro = formpagdinheiro;
	}
	public Debito getFormpagdebito() {
		return formpagdebito;
	}

	public void setFormpagdebito(Debito formpagdebito) {
		this.formpagdebito = formpagdebito;
	}

	public Credito getFormpagcredito() {
		return formpagcredito;
	}

	public void setFormpagcredito(Credito formpagcredito) {
		this.formpagcredito = formpagcredito;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	private BigDecimal validadorPGCreditoValorTotal(Credito cd, Dinheiro dh, Debito db, BigDecimal valortotal) {
			
		if(cd.getCredito().equals("Crédito") && (dh.getDinheiro().equals("Dinheiro"))) {
			return valortotal;	
		}else if(cd.getCredito().equals("Crédito") && (db.getDebito().equals("Débito"))) {
			return valortotal;
	    }else if(cd.getCredito().equals("Crédito") && (!cd.getValorcredito().equals(new BigDecimal("0.00")))) {
	    	return cd.getValorcredito(); 
	    }else {
	    	return valortotal;
	    }
			
	}
		
}
