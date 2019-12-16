package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.repository.ItemRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorrepo;
	
	@Autowired
	private ItemRepository itemrepo;
	
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	
	public void atualizar(Fornecedor fornecedor, ModelAndView mvfn) {
		
		fornecedores = fornecedorrepo.buscarFornecedor(fornecedor.getCnpj());
		
		List<Item> itens = new ArrayList<Item>();
        		       	
		fornecedores.forEach(f->{ f.getItens().forEach(item ->{ itens.add(item); }); });
				
		fornecedor.setItens(itens);
		
		fornecedorrepo.save(fornecedor);
		
		mvfn.addObject("fornecedores", fornecedores);
			    		  			
	}
	
	public void exibirItens(String cnpj, ModelAndView mvfn) {
				
		fornecedores = fornecedorrepo.buscarFornecedor(cnpj);
		
		List<Item> itens = new ArrayList<Item>();
			       	
	    fornecedores.forEach(f->{ f.getItens().forEach(item ->{ itens.add(item); }); });
	    
        mvfn.addObject("item", itens);
	 	
	}
	   
	public void addItemFornecedor(String codigoitem, String cnpj, ModelAndView mvfn) {
		
		 fornecedores = fornecedorrepo.buscarFornecedor(cnpj);
		        			             		 
		 List<Item> itens = itemrepo.buscarItemEstoque(codigoitem);
		   
		 fornecedores.iterator().next().getItens().addAll(itens);
		 
	     fornecedorrepo.saveAll(fornecedores);	 
			    	  	  		      		    
		  mvfn.addObject("item", itens);	        				
	    	  
	}
			
}




