package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.CaixaAbertura;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.models.Pedido;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.repository.CaixaAberturaRepository;
import br.com.devrdgao.controleloja.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendarepo;
	
	@Autowired
	private CaixaAberturaRepository caixaberturarepo;
		
	public List<Pedido> exibirPedidoVenda(Long codigovenda) {
		
	   List<Pedido> vendaspedido = new ArrayList<Pedido>();			
		               
       Iterable<Venda> venda = vendarepo.buscarVendas(codigovenda);              
      
       venda.forEach(itenvenda -> itenvenda.getPedidos().forEach(pedido -> vendaspedido.add(pedido)));
               
	   return vendaspedido; 	
	  
	}
	
	public CaixaAbertura buscaAberturaValorCaixa() {
		
	    List<CaixaAbertura> cxAbertura = caixaberturarepo.findAll();
        
		if(!cxAbertura.isEmpty()) {
					    
		   return cxAbertura.get(cxAbertura.size()-1); 	
			
		}
			
	   return null;
	}
	
	public List<CaixaAbertura> buscaTodasAberturaCaixa(){
		
	    List<CaixaAbertura> cxAbertura = caixaberturarepo.findAll();
		
	    return cxAbertura;
		
	}
			
	
}
