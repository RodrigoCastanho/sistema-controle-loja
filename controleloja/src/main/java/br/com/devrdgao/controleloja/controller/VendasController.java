package br.com.devrdgao.controleloja.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.models.formapagamento.Debito;
import br.com.devrdgao.controleloja.repository.VendaRepository;
import br.com.devrdgao.controleloja.service.BigDecimalConverter;
import br.com.devrdgao.controleloja.service.DataConverter;

@Controller
public class VendasController {
	
	@Autowired
	private VendaRepository vendarepo;
	
	private Iterable<Venda> vendas = new ArrayList<Venda>();
	
	@GetMapping("vendas")
	public ModelAndView vendas() {
		
		ModelAndView mvvd = new ModelAndView("vendas");
		return mvvd;
		
	}
   

	@GetMapping("/buscarvendas")
	public ModelAndView buscarVendas(@RequestParam(value="datainicial") Date datainicial,
			                         @RequestParam(value="datafinal")   Date datafinal, 
			                         @RequestParam(value="codigovenda") Long codigovenda) {
		
	  ModelAndView mvvd = new ModelAndView("vendas");
	  vendas = vendarepo.buscarVendas(datainicial, datafinal);	  
	  mvvd.addObject("vendas", vendas);

	  return mvvd;	
		
	}
	
	@GetMapping("/itensvenda") 
	public ModelAndView itensVenda(@RequestParam(value="codigovenda") Long codigovenda) {
		
	  ModelAndView mvvd = new ModelAndView("vendas");
	  
      vendas.forEach(itensvenda ->{
    	  
    	  if(itensvenda.getCodigovenda().equals(codigovenda)) {
    	        
    		  mvvd.addObject("itensvenda", itensvenda.getItens());
    
    	  }
  
      });
	
	  return mvvd;
			  
	}
	

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
		binder.registerCustomEditor(Date.class, new DataConverter());


	}
	
}
