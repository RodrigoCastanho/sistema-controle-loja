package br.com.devrdgao.controleloja.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.CaixaAbertura;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.models.Pedido;
import br.com.devrdgao.controleloja.models.SaqueCaixa;
import br.com.devrdgao.controleloja.models.Usuario;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.models.formapagamento.ColetaFormasPagamento;
import br.com.devrdgao.controleloja.models.formapagamento.Credito;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.models.formapagamento.Dinheiro;
import br.com.devrdgao.controleloja.repository.CaixaAberturaRepository;
import br.com.devrdgao.controleloja.repository.PedidoRepository;
import br.com.devrdgao.controleloja.repository.SaqueCaixaRepository;
import br.com.devrdgao.controleloja.repository.UsuarioRepository;
import br.com.devrdgao.controleloja.repository.VendaRepository;
import net.bytebuddy.implementation.bytecode.Throw;

   
@Service
public class CaixaService {
	
	private List<Item> itenspedido = new ArrayList<Item>();
	private BigDecimal descporcent = new BigDecimal("0.00");
	private BigDecimal descontoreal = new BigDecimal("0.00");
	private BigDecimal descontos = new BigDecimal("0.00");
	private BigDecimal valortotal = new BigDecimal("0.00");
	private int codigo = 0; /*<-Variavel usada para incrementar o valor do código a cada chamada do metodo "adicionarItemNãoCadastrado"
	                         sempre ter um código com valor diferente caso usario insira mais itens desse porte. */
	@Autowired
	private VendaRepository vendarepo;
	
	@Autowired
	private PedidoRepository pedidorepo;
	
	@Autowired
	private EstoqueService estoqueservice;
	
	@Autowired
	private VendaService vendaservice;
	
	@Autowired
	private UsuarioRepository usuariorepo;
	
	@Autowired
	private SaqueCaixaRepository caixarepo;
	
	@Autowired
	private CaixaAberturaRepository caixaberturarepo;
	
	@Autowired
	private ImpressaoService impressaoservice;
		
	private Usuario usuario = new Usuario();
	private CaixaAbertura cxabertura = new CaixaAbertura();
	
	public void calculoValoresItem(List<Item> item, ModelAndView mv, String quantidade, BigDecimal desconto, String descontop) {
		
		try { 
		   
		   BigDecimal precoitem = item.iterator().next().getPrecovenda();
		   BigDecimal quantitem = new BigDecimal(quantidade);
           BigDecimal totalitem = new BigDecimal("0.00");
		   BigDecimal valortotalitem = new BigDecimal("0.00");
		   BigDecimal porcentagem = new BigDecimal("0.00");

		   totalitem = precoitem.multiply(quantitem);
		   porcentagem = (new BigDecimal(descontop)).divide(new BigDecimal(100));
		   descporcent = totalitem.multiply(porcentagem).setScale(2, RoundingMode.HALF_EVEN); 
		   descontoreal = desconto;

		   descontos = descontos.add(descporcent).add(descontoreal);
           	   
		   valortotalitem = totalitem.subtract(descporcent).subtract(descontoreal);
		   
		   item.iterator().next().setValoritem(precoitem);
		   item.iterator().next().setPrecovenda(valortotalitem);
		   item.iterator().next().setQuantidade(Integer.valueOf(quantidade));
		   item.iterator().next().setObterdesconto(descporcent.add(descontoreal));
		                
           valortotal = valortotal.add(valortotalitem);
           		   
		   itenspedido.addAll(item);
		   		   
		   mv.addObject("listapedido", itenspedido);
		   mv.addObject("total",valortotal);
		   
		  		    		    
		}catch (NoSuchElementException e) {
			
	    	  System.out.println(" Erro " + e);
	    	  	  
		} catch (NumberFormatException e) {
			
	    	  System.out.println(" Erro " + e);

		}

	}
	
	public void adicionarItemNaoCadastrado(String descricao, BigDecimal preco, ModelAndView mv) {
		
		 Item item = new Item(); 
	   
		 codigo++;
	     
	     item.setCodigoitem("---------"+String.valueOf(codigo));
	     item.setDescricao(descricao);
	     item.setQuantidade(1);
	     item.setPrecovenda(preco);
		
		 valortotal = valortotal.add(preco);
	     itenspedido.add(item);
          
		 mv.addObject("listapedido", itenspedido);
		 mv.addObject("total",valortotal);
			
		
	}
	
	public void deletarItemLista(String codigoitem, ModelAndView mv) {
			
		for (int p = 0; p < itenspedido.size(); p++) {
			
			if(codigoitem.equals(itenspedido.get(p).getCodigoitem())) {
					 
				 valortotal = valortotal.subtract(itenspedido.get(p).getPrecovenda());
				 descontos = descontos.subtract(itenspedido.get(p).getObterdesconto()); 
				 
				 itenspedido.get(p).setPrecovenda(valortotal);
				 
				 itenspedido.remove(p);
				 				 			
				 mv.addObject("listapedido", itenspedido);
				 mv.addObject("total",valortotal);	
				
			}
			 	
		} 
		 		
	}
	
	public void aberturaCaixa(BigDecimal valorinicial, String sessaousuario) {
		
		LocalDate dataabertura = LocalDate.now();
		usuario = usuariorepo.findByLogin(sessaousuario);
        
		cxabertura.setDataabertura(dataabertura);
		cxabertura.setValorabertura(valorinicial);
		cxabertura.setValorfechamento(valorinicial);
		cxabertura.setUsuario(usuario);
         		
		caixaberturarepo.save(cxabertura);
						
	} 
			
	public void saqueValorCaixa(BigDecimal valorretirado, String justificativa, String sessaousuario) {
		
		SaqueCaixa sqcaixa = new SaqueCaixa();
		
		LocalDate datasaque = LocalDate.now();
		usuario = usuariorepo.findByLogin(sessaousuario);
	  	  
		sqcaixa.setDatasaque(datasaque);
		sqcaixa.setValorretirado(valorretirado);
		sqcaixa.setJustificativa(justificativa);
		sqcaixa.setUsuario(usuario);
		
		cxabertura = vendaservice.buscaAberturaValorCaixa();
		cxabertura.calculoValorFechamento(valorretirado); 
					
		caixarepo.save(sqcaixa);
		caixaberturarepo.save(cxabertura);
		 		 	 	
	} 
	
	protected void fluxoValorCaixa(BigDecimal troco) {
				
		if(!troco.equals(new BigDecimal("0.00"))) {
				
			 new Thread(() ->{	
				     cxabertura = vendaservice.buscaAberturaValorCaixa();   
				 	 cxabertura.calculoValorFechamento(troco); 
					 caixaberturarepo.save(cxabertura);
			   }).start();	
									
		}
				
	}  
	
	protected BigDecimal descontoValorTotal(String desconto) {
		
      BigDecimal totaldesconto = new BigDecimal("0.00");
		
		if(desconto != "") {
			  
          BigDecimal porcentagem = (new BigDecimal(desconto)).divide(new BigDecimal(100));	
          totaldesconto = valortotal.multiply(porcentagem).setScale(2, RoundingMode.HALF_EVEN); 
          descontos = descontos.add(totaldesconto);
		  return totaldesconto;
		  
		}
		return totaldesconto; 
		
	}
				
	public void concluirCompra(ColetaFormasPagamento fpagamento, ModelAndView mvcx, String sessaousuario) {
		
		LocalDateTime datahora = LocalDateTime.now();
      		
	  if(!itenspedido.isEmpty()) {

		Dinheiro dinheiro = new Dinheiro(fpagamento.getDinheiro(), fpagamento.getValorrecebido(), fpagamento.getTroco(), valortotal);
		Debito debito = new Debito(fpagamento.getDebito(), valortotal); 
		Credito credito = new Credito(fpagamento.getCredito(), fpagamento.getParcela(), fpagamento.getValorparcela(), valortotal);
		
		valortotal = valortotal.subtract(descontoValorTotal(fpagamento.getDesconto()));
		
	    List<Pedido> pedidos = new ArrayList<Pedido>();
	    estoqueservice.controleQuantEstoque(itenspedido, mvcx);
	    
	    fluxoValorCaixa(dinheiro.getValortroco());
	    
	    itenspedido.forEach(i -> { 
	    	   
	      Pedido pedido = new Pedido(i.getCodigoitem(), i.getDescricao(), i.getQuantidade(), i.getValoritem(), i.getPrecovenda(), 
	    		  		             datahora, valortotal, dinheiro, debito, credito);  
		  pedidos.add(pedido); 	  

	    });
	    	    
	    usuario = usuariorepo.findByLogin(sessaousuario);
	   	    
		pedidorepo.saveAll(pedidos); 

		Venda venda = new Venda(datahora.withSecond(0).withNano(0), dinheiro, debito, credito, descontos, valortotal, pedidos, usuario);
				
		vendarepo.save(venda);
		
		pedidos.iterator().next().setCodigovendacnf(venda.getCodigovenda());
						
		impressaoservice.impremirPedidos(pedidos);
								
        itenspedido.clear();
        valortotal = new BigDecimal("0.00");
        descontos = new BigDecimal("0.00");
       
        
	  } else { 
		  
		System.out.println("Não tem pedidos...");
		
				  
	  }  
             			
	}			
}
