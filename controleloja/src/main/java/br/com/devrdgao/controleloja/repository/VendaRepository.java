package br.com.devrdgao.controleloja.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.devrdgao.controleloja.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, String> {
	
	@Query(value="SELECT * FROM venda WHERE DATE(data) BETWEEN :datainicial AND :datafinal ", nativeQuery = true)
	List<Venda> buscarVendas(@Param("datainicial") Date datainicial, 
							 @Param("datafinal") Date datafinal);
	
	@Query(value="SELECT * FROM venda WHERE codigovenda =:codigovenda", nativeQuery = true)
	List<Venda> buscarVendas(@Param("codigovenda") Long codigovenda);
	
	
		
}
