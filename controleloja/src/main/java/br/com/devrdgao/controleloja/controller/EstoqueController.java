package br.com.devrdgao.controleloja.controller;

import java.math.BigDecimal;
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
import br.com.devrdgao.controleloja.repository.ItemRepository;
import br.com.devrdgao.controleloja.service.BigDecimalConverter;
import br.com.devrdgao.controleloja.service.EstoqueService;


@Controller
public class EstoqueController {
		
	@Autowired
	private ItemRepository itemrepo;
	
	@Autowired
	private EstoqueService estoqueservice;
		
	@GetMapping("estoque")
	public ModelAndView estoque() {
		
		ModelAndView mve = new ModelAndView("estoque");
		return mve;
	  
	}
	
	@GetMapping("buscaritem")
	public ModelAndView buscarItem(@RequestParam(value="buscar") String buscar) {
		
	    Iterable<Item> item = itemrepo.buscarItemEstoque(buscar);
		ModelAndView mve = new ModelAndView("estoque");
		mve.addObject("item", item);		
	    return mve;	
			  	
	}
	
	@GetMapping("/buscartodos")
	public ModelAndView buscarTodosItens() {
		
		ModelAndView mve = new ModelAndView("estoque");
	    Iterable<Item> itemtodos = itemrepo.findAll();
	    Long itenscadastrado = itemrepo.count();
		mve.addObject("item", itemtodos);
		mve.addObject("itenscadastrado", itenscadastrado);
		return mve;
		
	}
	
	@PostMapping("/editar")
	public ModelAndView editarItem(@ModelAttribute(name="item") Item item) {
		
		  itemrepo.save(item);
		  
		  return new ModelAndView("estoque");
		
	}
	
	@GetMapping("deletar{codigoitem}")
	public ModelAndView deletarItem(@PathVariable(value="codigoitem") String codigoitem) {
				 
		  estoqueservice.deletarItemEstoque(codigoitem);
		
		  
	      return estoque();
	     
     }

	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
       	
	}
	
}
