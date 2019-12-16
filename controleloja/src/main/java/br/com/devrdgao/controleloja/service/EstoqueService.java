package br.com.devrdgao.controleloja.service;

import java.util.ConcurrentModificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.repository.ItemRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private FornecedorRepository fornecedorrepo;
	
	@Autowired
	private ItemRepository itemrepo;
	
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
