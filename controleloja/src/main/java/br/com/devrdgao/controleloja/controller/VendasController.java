package br.com.devrdgao.controleloja.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.repository.VendaRepository;
import br.com.devrdgao.controleloja.service.BigDecimalConverter;
import br.com.devrdgao.controleloja.service.DataConverter;
import br.com.devrdgao.controleloja.service.VendaService;

@Controller
public class VendasController {
	
	@Autowired
	private VendaRepository vendarepo;
	
	@Autowired
	private VendaService vendaservice;
			
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
	  Iterable<Venda> venda = vendarepo.buscarVendas(datainicial, datafinal, codigovenda);
	  BigDecimal totalvenda = vendarepo.totalVenda(datainicial, datafinal, codigovenda);
	  mvvd.addObject("vendas", venda);
	  mvvd.addObject("totalvenda", totalvenda);

	  return mvvd;	
		
	} 
	
	@GetMapping("itensvenda") 
	public ModelAndView itensVenda(@RequestParam(value="codigovenda") Long codigovenda) {
			
	    ModelAndView mvvd = new ModelAndView("vendatabelaitens");
    
		mvvd.addObject("vendaspedido", vendaservice.exibirPedidoVenda(codigovenda));
        
	    return mvvd;
			  
	}  
	
	@GetMapping("imprimevenda")
	public ModelAndView imprimeCupomVendaEfetuada(@RequestParam(value="codigovendaimp") Long codigovendaimp,
			                                       HttpServletResponse response) {
		   
	   vendaservice.imprimePedidoVenda(codigovendaimp, response);
	
       return null;
	}
	
	@GetMapping("/buscar")
	public ModelAndView buscarAberturaCaixa() {
		
		
	    ModelAndView mvvd = new ModelAndView("vendatabelacaixa");
		mvvd.addObject("valoremcaixa", vendaservice.buscaAberturaValorCaixa());
    
	  
	  return mvvd;	
		
	}
	
	@GetMapping("/buscartudo")
	public ModelAndView buscarTodaAberturaCaixa() {
		
	    ModelAndView mvvd = new ModelAndView("vendatabelacaixa");
	    mvvd.addObject("valoremcaixa", vendaservice.buscaTodasAberturaCaixa()); 
	    	
		return mvvd;
		
	}
	
	@GetMapping("/buscarbaixascaixa")
	public ModelAndView buscarBaixasCaixa() {
		
		
	    ModelAndView mvvd = new ModelAndView("tabelavendabxcaixa");
		mvvd.addObject("baixascaixa", vendaservice.buscaTodosSaqueCaixa());
    
	  
	  return mvvd;	
		
	}
	
	@PostMapping("/deletarOpCaixa")
	public ModelAndView deletarDadosCaixa(@RequestParam(value="group1") String selecao) {
		
	    ModelAndView mvvd = new ModelAndView("vendas");	    
	    vendaservice.deletarOpCaixa(selecao);	
        return mvvd;	
		
	}
			
	@GetMapping("venda{codigovenda}")
	public ModelAndView deletarVenda(@PathVariable(value="codigovenda") Long codigovenda) {
		
		List<Venda> venda = vendarepo.buscarVendas(codigovenda);
		vendarepo.deleteAll(venda);
		venda.clear();
		
	    return new ModelAndView("vendas");
	     
     }
	      	
	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
		binder.registerCustomEditor(Date.class, new DataConverter());


	}
	
}
