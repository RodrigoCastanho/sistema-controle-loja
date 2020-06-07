package br.com.devrdgao.controleloja.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.devrdgao.controleloja.models.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {
	
	@Query(value="SELECT * FROM fornecedor WHERE cnpj =:cnpjnome OR nome LIKE:cnpjnome", nativeQuery = true)
	 List<Fornecedor> buscarFornecedor(@Param("cnpjnome") String cnpjnome);
	 
	
		
}




