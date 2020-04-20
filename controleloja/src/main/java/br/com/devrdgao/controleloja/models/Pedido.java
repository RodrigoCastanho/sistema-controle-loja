package br.com.devrdgao.controleloja.models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import br.com.devrdgao.controleloja.models.formapagamento.Credito;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.models.formapagamento.Dinheiro;
=======
import br.com.devrdgao.controleloja.models.formapagamento.ColetaFormasPagamento;

>>>>>>> 646df419dc62f550a431b1dd2f01a60ce08cf1b2


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
			      LocalDateTime data, BigDecimal valortotal, Dinheiro dh, Debito db, Credito cd) {
		
		this.codigoitem = codigoitem;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valoritem = valoritem;
		this.precoitem = precoitem;
		super.datacnf = data;
<<<<<<< HEAD
		super.totalcnf = valortotal.subtract(dh.getDesconto());
		super.tipopagamentocnf = dh.getDinheiro()
							    .concat(db.getDebito())
							    .concat(cd.getCredito()); 
		super.trococnf = dh.getValortroco();
=======
		super.totalcnf = valortotal;
		super.tipopagamentocnf = pagamento.getDinheiro()
							    .concat(pagamento.getDebito())
							    .concat(pagamento.getCredito()); 
		super.trococnf = pagamento.getTroco();
>>>>>>> 646df419dc62f550a431b1dd2f01a60ce08cf1b2
		
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
		DateTimeFormatter dataformat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return dataformat.format(super.datacnf);
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
		return df.format(super.totalcnf);
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
