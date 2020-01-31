package br.com.devrdgao.controleloja.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NoPermissionException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.repository.ItemRepository;
import br.com.devrdgao.controleloja.service.BigDecimalConverter;
import br.com.devrdgao.controleloja.service.DataConverter;

@Controller
public class CadastroItemController {
	
	@Autowired
	private ItemRepository itemrepo;
	
	@Autowired
	private FornecedorRepository fornecedorrepo;
	
	private List<Item> itens = new ArrayList<Item>();
	
	@GetMapping("cadastraritem")
	public ModelAndView cadastroItem(Item item) {
		ModelAndView mv = new ModelAndView("cadastrarItem");
		return mv;
	
	}
		
	@PostMapping("/cadastrar")
	public ModelAndView cadastro(@ModelAttribute(name="item") @Valid Item item, BindingResult result, RedirectAttributes attrib) {
		
		if(result.hasErrors()){
			
			System.out.println("Validação ativa");

		    return cadastroItem(item);

		}
		        
		itens.add(item); 
		itemrepo.save(item);
				
		attrib.addFlashAttribute("msncadastrar","Item cadastrado com Sucesso!");
		
	    return new ModelAndView("redirect:/cadastraritem");
	   
	}
	
	@PostMapping("/addfornecedor")
	public ModelAndView cadastroFornecedor(@ModelAttribute(name="fornecedor") Fornecedor fornecedor , RedirectAttributes attrib) {
			    
         fornecedor.setItens(itens);
         fornecedorrepo.save(fornecedor);
		 itens.clear();
	
		 attrib.addFlashAttribute("msncadastrar","Fornecedor cadastrado com Sucesso!");

	     return new ModelAndView("redirect:/cadastraritem");	    
				
	}
		
	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(BigDecimal.class, new BigDecimalConverter());
		binder.registerCustomEditor(Date.class, new DataConverter());

	}
	

}
