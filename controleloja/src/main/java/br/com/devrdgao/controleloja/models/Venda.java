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
    
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name="venda_itens", 
			   joinColumns = @JoinColumn(name = "cod_venda"),
			   inverseJoinColumns = @JoinColumn(name = "item_cod"))
    
	private List<Item> itens;
	
	public Venda(LocalDateTime data, Dinheiro formpagdinheiro, Debito formpagdebito, Credito formpagcredito,
			BigDecimal desconto, BigDecimal valorvenda, List<Item> itens) {
		super();
		
		this.data = data;
		this.formpagdinheiro = formpagdinheiro;
		this.formpagdebito = formpagdebito;
		this.formpagcredito = formpagcredito;
		this.desconto = desconto;
		this.valorvenda = valorvenda;
		this.itens = itens;
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

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
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
	
}
