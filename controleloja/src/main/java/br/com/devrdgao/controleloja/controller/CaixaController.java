package br.com.devrdgao.controleloja.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.models.Usuario;
import br.com.devrdgao.controleloja.models.formapagamento.ColetaFormasPagamento;
import br.com.devrdgao.controleloja.repository.ItemRepository;
import br.com.devrdgao.controleloja.repository.UsuarioRepository;
import br.com.devrdgao.controleloja.service.BigDecimalConverter;
import br.com.devrdgao.controleloja.service.DataConverter;
import br.com.devrdgao.controleloja.service.EstoqueService;
import br.com.devrdgao.controleloja.service.CaixaService;

@Controller
public class CaixaController {

	@Autowired
	private ItemRepository itemrepo;

	@Autowired
	private CaixaService caixaservice;

	@Autowired
	private EstoqueService estoqueservice;

	private List<Item> itens = new ArrayList<Item>();

	@GetMapping("/")
	public ModelAndView caixa() {

		ModelAndView mvcx = new ModelAndView("index");
		return mvcx;

	}

	@GetMapping("itemcaixa")
	public ModelAndView buscarItem(@RequestParam(value = "buscaritem") String buscaritem) {

		List<Item> item = itemrepo.buscarItemCaixa(buscaritem);
		itens = item;
		ModelAndView mvcx = new ModelAndView("descricaovaloritem");
		mvcx.addObject("itemdocaixa", item);

		
		return mvcx;  
				
	} 
	
	@GetMapping("adicionar")
	public ModelAndView adicionarItem(@RequestParam(value="quantidade") String quantidade,
			                          @RequestParam(value="desc") BigDecimal desconto,
			                          @RequestParam(value="descp") String descontop) {
		        	   
		       ModelAndView mvcx = new ModelAndView("index");
       
		       caixaservice.calculoValoresItem(itens, mvcx, quantidade, desconto, descontop);
		       
		return mvcx;	
	
	}
	
	@PostMapping("adicionaritem")
	public ModelAndView adicionarItemNaoCadastrado(@RequestParam(value="descricaoitem") String descricao, 
												   @RequestParam(value="precoitem") BigDecimal preco) {
	  
        			       
		    ModelAndView mvcx = new ModelAndView("index");
		    
		    caixaservice.adicionarItemNaoCadastrado(descricao, preco, mvcx);
            
	   return mvcx;
		
	}
	
	@GetMapping("{codigoitem}")
	public ModelAndView deletarItem(@PathVariable(value="codigoitem") String codigoitem) {
				   
		   ModelAndView mvcx = new ModelAndView("index");
		    
		   caixaservice.deletarItemLista(codigoitem, mvcx);
		   
		return mvcx;
		
	}
	

	@GetMapping("valorcaixa")
	public ModelAndView valorCaixa(@RequestParam(value = "valorinicial") BigDecimal valorinicial, 
								   @RequestParam(value = "sessaousuario") String sessaousuario) {
		
		ModelAndView mvcx = new ModelAndView("index");
		
		caixaservice.aberturaCaixa(valorinicial, sessaousuario);
				
	    return mvcx;	
	}
	

	@PostMapping("retiradacaixa")
	public ModelAndView retiradaCaixa(@RequestParam(value = "retiradacaixa") BigDecimal valorretirado,
			                          @RequestParam(value = "justificativa") String justificativa,
			                          @RequestParam(value = "sessaousuario") String sessaousuario) {

		ModelAndView mvcx = new ModelAndView("index");
		
		caixaservice.saqueValorCaixa(valorretirado, justificativa, sessaousuario);

		return mvcx;
	}

	@PostMapping("/concluircompra")
	public ModelAndView concluirCompra(@ModelAttribute("ColetaFormasPagamento") ColetaFormasPagamento formaspagamento, 
			                           @RequestParam(value = "sessaousuario") String sessaousuario) {

		ModelAndView mvcx = new ModelAndView("index");

		caixaservice.concluirCompra(formaspagamento, mvcx, sessaousuario);

		return mvcx;
	}

	@InitBinder
	public void iniBinder(WebDataBinder binder) {

		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
		binder.registerCustomEditor(Date.class, new DataConverter());

	}

}
