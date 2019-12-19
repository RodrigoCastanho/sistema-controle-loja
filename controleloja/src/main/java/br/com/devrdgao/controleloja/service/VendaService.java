package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendarepo;
	
	
	public List<Item> exibirItensVenda(Long codigovenda) {
		    
	   List<Item> itensvenda = new ArrayList<Item>();		
           
       Iterable<Venda> venda = vendarepo.buscarVendas(codigovenda);              
      
       venda.forEach(itenvenda -> itenvenda.getItens().forEach(item -> itensvenda.add(item)));
               
	   return itensvenda;	
	  
	}

}
