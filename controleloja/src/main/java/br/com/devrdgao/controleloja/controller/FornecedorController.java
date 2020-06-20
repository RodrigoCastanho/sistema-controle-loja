package br.com.devrdgao.controleloja.controller;


import java.sql.Date;
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

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.service.DataConverter;
import br.com.devrdgao.controleloja.service.FornecedorService;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorrepo;
		
	@Autowired
    private FornecedorService fornecedorservice;	
	
	
	@GetMapping("fornecedor")
	public ModelAndView fornecedor() {
		
		ModelAndView mvfn = new ModelAndView("fornecedor");
		return mvfn;
		
	}
	
	@GetMapping("buscarfornecedor")
	public ModelAndView buscaFornecedor(@RequestParam(value="cnpjnome") String cnpjnome) {
					   
		Iterable<Fornecedor> fornecedor = fornecedorrepo.buscarFornecedor(cnpjnome);
		ModelAndView mvfn = new ModelAndView("fornecedor");
		mvfn.addObject("fornecedores", fornecedor);		
		
	    return mvfn;  
				  	
	}
	
	@GetMapping("/buscartodosfornecedores")
	public ModelAndView buscarTodosFornecedor() {
		
	  Iterable<Fornecedor> fornecedor = fornecedorrepo.findAll();	
	  ModelAndView mvfn = new ModelAndView("fornecedor");
	  mvfn.addObject("fornecedores", fornecedor);
	  
	  return mvfn;
	  
	}
	
	
	@GetMapping("exibiritens")
	public ModelAndView exibirItem(@RequestParam(value="cnpj") String cnpj) {
				
		ModelAndView mvfn = new ModelAndView("fornecedortabelaitens");	
		fornecedorservice.exibirItens(cnpj, mvfn);
		
	    return mvfn;
	 
	}
	
	@GetMapping("additem")
	public ModelAndView adicionarItem(@RequestParam(value="codigoitem") String codigoitem,
			                          @RequestParam(value="cnpj") String cnpj) {
		
		ModelAndView mvfn = new ModelAndView("fornecedortabelaitens");	
		fornecedorservice.addItemFornecedor(codigoitem, cnpj, mvfn);
	    		
		return mvfn;
	}
	
	
	@PostMapping("/editarfornecedor")
	public ModelAndView editarFornecedor(@ModelAttribute(name="fornecedor") Fornecedor fornecedor) {
		
		ModelAndView mvfn = new ModelAndView("fornecedor");
		fornecedorservice.atualizar(fornecedor, mvfn);
					
		return mvfn;
		
	}
	
	@GetMapping("deleta{cnpjcodigo}")
	public ModelAndView deletarFornecedor(@PathVariable(value="cnpjcodigo") String cnpjcodigo) {
		
		fornecedorrepo.deleteById(cnpjcodigo);
		
		return new ModelAndView("fornecedor");
		
	}
	
	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Date.class, new DataConverter());
		
	}
	
}
