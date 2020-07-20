package br.com.devrdgao.controleloja.service;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Fornecedor;
import br.com.devrdgao.controleloja.models.Item;
import br.com.devrdgao.controleloja.repository.FornecedorRepository;
import br.com.devrdgao.controleloja.repository.ItemRepository;

@Service
public class EstoqueService {

	@Autowired
	private FornecedorRepository fornecedorrepo;

	@Autowired
	private ItemRepository itemrepo, itemrepo1;

	private List<Item> itens = new ArrayList<Item>();

	public void controleQuantEstoque(List<Item> itenspedido, ModelAndView mv) {

		List<Item> calculoestoque = new ArrayList<Item>();
		
		Optional<Item> item = itemrepo.findById(itenspedido.iterator().next().getCodigoitem());

		calculoestoque.add(item.filter(ite -> ite.getCodigoitem().equals(itenspedido.iterator().next().getCodigoitem())).get());
		calculoestoque.forEach(ite -> { 
				ite.setQuantidade(ite.getQuantidade()-itenspedido.iterator().next().getQuantidade());
				itens.add(ite);
				itemrepo.saveAll(calculoestoque);
				itens.forEach(its -> {
				if (its.getQuantidade() <= its.getQuantminima()) {
					its.setQuantidade(0);
					mv.addObject("notificacao", "Itens em falta no estoque!");
					mv.addObject("itensfalta", itens.stream().filter(it -> it.getQuantidade() <= it.getQuantminima())
							                                 .collect(Collectors.toList()));
				}
			});
		});
	}
	
	public void controleQuantEstoqueReporQuantItem(String codigoitem, Integer quantidade) {
		 		
		if((codigoitem != null) && (!codigoitem.isEmpty()) && (quantidade != null)) { 
			for(int p = 0; p < itens.size(); p++) {
				if (codigoitem.equals(itens.get(p).getCodigoitem())) {
		    	    itens.get(p).setQuantidade(itens.get(p).getQuantidade()+quantidade); 
		    	    itemrepo1.save(itens.get(p)); 
		    	    itens.remove(p);	    
		        }		
		    }
	  	
		}
	}

	public void limparItensListNotificaoEstoque() {
		   itens.clear();
	}

	public void deletarItemEstoque(String codigoitem) {

		Iterable<Fornecedor> fornecedor = fornecedorrepo.findAll();
		
		fornecedor.forEach(listitem -> {
			try {

				listitem.getItens().forEach(itens -> {

					listitem.getItens().removeIf(codigo -> codigoitem.equals(itens.getCodigoitem()));
				});

			} catch (ConcurrentModificationException e) {
				System.out.println(" Erro " + e);

			}

		});

		fornecedorrepo.saveAll(fornecedor);
		itemrepo.deleteById(codigoitem);
	}
}
