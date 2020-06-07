package br.com.devrdgao.controleloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devrdgao.controleloja.models.SaqueCaixa;

@Repository
public interface SaqueCaixaRepository extends JpaRepository<SaqueCaixa, String> {
	
	
}
