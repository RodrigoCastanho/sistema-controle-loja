package br.com.devrdgao.controleloja.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.devrdgao.controleloja.models.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	
     //Busca para Estoque
	@Query(value="SELECT * FROM item i WHERE i.codigoitem =:codigodesc OR i.descricao LIKE:codigodesc", nativeQuery = true)
	 List<Item> buscarItemEstoque(@Param("codigodesc") String codigodesc);
    
	//Busca Caixa
	 @Query(value="SELECT * FROM item i WHERE i.codigoitem =:codigoitem", nativeQuery = true)
	 List<Item> buscarItemCaixa(@Param("codigoitem") String codigoitem);
    
  
}
