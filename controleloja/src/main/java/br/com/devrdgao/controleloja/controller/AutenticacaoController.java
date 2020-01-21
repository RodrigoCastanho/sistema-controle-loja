package br.com.devrdgao.controleloja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AutenticacaoController {

	@GetMapping("/acessar")
	public ModelAndView acesso() {
		
		 ModelAndView mvlg = new ModelAndView("login");
		 return mvlg; 
	  	 
	}
	
}
