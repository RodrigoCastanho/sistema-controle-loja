package br.com.devrdgao.controleloja.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.models.formapagamento.ColetaFormasPagamento;
import br.com.devrdgao.controleloja.models.formapagamento.Credito;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.models.formapagamento.Dinheiro;
import br.com.devrdgao.controleloja.repository.VendaRepository;

   
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
	
	public void calculoValoresItem(List<Item> item, ModelAndView mv, String quantidade, BigDecimal desconto, String descontop) {
			
		try { 
		   
		   BigDecimal precoitem = item.iterator().next().getPrecovenda();
		   BigDecimal quantitem = new BigDecimal(quantidade);
           BigDecimal totalitem = new BigDecimal("0.00");
		   BigDecimal valortotalitem = new BigDecimal("0.00");

		   BigDecimal porcentagem = new BigDecimal("0.00");

		   totalitem = precoitem.multiply(quantitem);
		   porcentagem = (new BigDecimal(descontop)).divide(new BigDecimal(100));
		   descporcent = totalitem.multiply(porcentagem).setScale(2, RoundingMode.DOWN);   
		   descontoreal = desconto;
		   
		   descontos = descontos.add(descporcent).add(descontoreal);

		   	   
		   valortotalitem = totalitem.subtract(descporcent).subtract(desconto);
		   
		   item.iterator().next().setValoritem(precoitem);
		   item.iterator().next().setPrecovenda(valortotalitem);
		   item.iterator().next().setQuantidade(Integer.valueOf(quantidade));
		   
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
	
	public void adicionarItemNãoCadastrado(String descricao, BigDecimal preco, ModelAndView mv) {
		
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
				 
				 itenspedido.get(p).setPrecovenda(valortotal);
				 
				 itenspedido.remove(p);
				 				 			
				 mv.addObject("listapedido", itenspedido);
				 mv.addObject("total",valortotal);	
				
			}
			 	
		} 
		 		
	}
      	
	public void concluirCompra(ColetaFormasPagamento fpagamento) {
		
		Venda venda = new Venda();
		LocalDateTime datahora = LocalDateTime.now();
		
	  if(!itenspedido.isEmpty()) {

		Dinheiro dinheiro = new Dinheiro(fpagamento.getDinheiro(),fpagamento.getValorrecebido(), fpagamento.getTroco(), descontos, valortotal);
		Debito debito = new Debito(fpagamento.getDebito(), descontos, valortotal); 
		Credito credito = new Credito(fpagamento.getCredito(),fpagamento.getParcela(), fpagamento.getValorparcela(), descontos, valortotal);
		
		venda.setData(datahora.withSecond(0).withNano(0));
		venda.setFormpagdinheiro(dinheiro);
		venda.setFormpagdebito(debito);
		venda.setFormpagcredito(credito);
		venda.setItens(itenspedido);
		venda.setDesconto(descontos);
		venda.setValorvenda(valortotal);
		
		vendarepo.save(venda);
								
        itenspedido.clear();
        valortotal = new BigDecimal("0.00");
        descontos = new BigDecimal("0.00");
        
	  }else {
		  
		System.out.println("Não tem pedidos...");
		
		  
	  }  
         
     			
	}
		
	
}
