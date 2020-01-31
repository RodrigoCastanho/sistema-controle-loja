package br.com.devrdgao.controleloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devrdgao.controleloja.models.SaqueCaixa;


public interface SaqueCaixaRepository extends JpaRepository<SaqueCaixa, String> {
	
	
}
