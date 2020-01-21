package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.repository.ItemRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private FornecedorRepository fornecedorrepo;
	
	@Autowired
	private ItemRepository itemrepo;
	
	private List<Item> itens = new ArrayList<Item>();

	public void controleQuantEstoque(List<Item> itenspedido, ModelAndView mvcx) {
		
	    List<String> iditens = new ArrayList<String>();
					
		for(Item itid: itenspedido) { 
			iditens.add(itid.getCodigoitem()); 
		}

		itens = itemrepo.findAllById(iditens);

	    for(Item it: itens) {
	      for(Item itp: itenspedido) {		 
	    	if(it.getCodigoitem().equals(itp.getCodigoitem())) {
	    	   it.setQuantidade(it.getQuantidade()-itp.getQuantidade());
	    	   itemrepo.saveAll(itens);
	    	      
	    	}
	      }	
	      
	        if(it.getQuantidade() <= it.getQuantminima()) {	
			  mvcx.addObject("notificacao", "Itens em falta no estoque!");
			  mvcx.addObject("itensfalta", itens);
			  
		    }	  
	     }
	       
	}
	
	public void deletarItemEstoque(String codigoitem) {
				
		Iterable<Fornecedor> fornecedor = fornecedorrepo.findAll(); 
  		
		fornecedor.forEach(listitem -> {
						 
		  try {
			  
			   listitem.getItens().forEach(itens -> {
				
			   listitem.getItens().removeIf(codigo -> codigoitem.equals(itens.getCodigoitem()));   	 
				                
			 });
			 
		     }catch (ConcurrentModificationException e) {
		    	 
		    	  System.out.println(" Erro " + e); 
		    	  	 
			} 

		});
		
		fornecedorrepo.saveAll(fornecedor);
		itemrepo.deleteById(codigoitem);
			
	}	
	
}
