package br.com.devrdgao.controleloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devrdgao.controleloja.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String>{
	
}
