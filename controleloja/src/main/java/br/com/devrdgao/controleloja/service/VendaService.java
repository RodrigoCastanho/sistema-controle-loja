package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.devrdgao.controleloja.models.CaixaAbertura;
import br.com.devrdgao.controleloja.models.Pedido;
import br.com.devrdgao.controleloja.models.SaqueCaixa;
import br.com.devrdgao.controleloja.models.Venda;
import br.com.devrdgao.controleloja.repository.CaixaAberturaRepository;
import br.com.devrdgao.controleloja.repository.SaqueCaixaRepository;
import br.com.devrdgao.controleloja.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendarepo;
	
	@Autowired
	private CaixaAberturaRepository caixaberturarepo;
	
	@Autowired
	private SaqueCaixaRepository sqcaixarepo;
	
	@Autowired
	private ImpressaoService impressaoservice;
		
	public List<Pedido> exibirPedidoVenda(Long codigovenda, boolean imprimir) {
	   
	   List<Pedido> vendaspedido = new ArrayList<Pedido>();			
		               
       Iterable<Venda> venda = vendarepo.buscarVendas(codigovenda); 
             
       venda.forEach(itenvenda -> itenvenda.getPedidos().forEach(pedido -> vendaspedido.add(pedido)));
       
       if(imprimir) {
    	   
    	  venda.forEach(v -> {         		          
    		  vendaspedido.forEach(vp -> {vp.setDatacnf(v.getData()); 
    		  							  vp.setCodigovendacnf(v.getCodigovenda());		
    			  	                      vp.setTipopagamentocnf(v.getFormpagdinheiro().getDinheiro()
    			  	                    		  				  .concat(v.getFormpagdebito().getDebito())
    			  	                    		  				  .concat(v.getFormpagcredito().getCredito()));     		  				  
    			  	                      vp.setTrococnf(v.getFormpagdinheiro().getValortroco());
    			  	                      vp.setTotalcnf(v.getValorvenda());
    			  
    		  });
    		     		  
    	  }); 
    	  
    	  impressaoservice.impremirPedidos(vendaspedido);
    	   
       }
               
	   return vendaspedido; 	
	  
	}
	
	public CaixaAbertura buscaAberturaValorCaixa() {
		
		    List<CaixaAbertura> cxAbertura = caixaberturarepo.findAll();
	        	   
				if(!cxAbertura.isEmpty()) {	      
			    	  
			    	  return cxAbertura.get(cxAbertura.size()-1); 		    	  
			    
		        } 
			   		  	   
		        return null;

	}
				
	public List<CaixaAbertura> buscaTodasAberturaCaixa() {
		
	    List<CaixaAbertura> cxAbertura = caixaberturarepo.findAll();
		
	    return cxAbertura;
		
	}
	
    public List<SaqueCaixa> buscaTodosSaqueCaixa() {
    	
	    List<SaqueCaixa> sqcaixa = sqcaixarepo.findAll();
	    
	    return sqcaixa;
		
	}
    
    public void deletarOpCaixa(String selecao) {
    	
    	  if(selecao.equals("fcaixa")) {
    		  caixaberturarepo.deleteAllInBatch();    		  		  
    	  } else if(selecao.equals("bcaixa")) {
    		  sqcaixarepo.deleteAllInBatch();   	  
    	  } else {}
    	 	
    	
    }
    
				
}
