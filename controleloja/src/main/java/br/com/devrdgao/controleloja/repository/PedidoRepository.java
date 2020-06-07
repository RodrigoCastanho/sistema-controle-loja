package br.com.devrdgao.controleloja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devrdgao.controleloja.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String>{
	
}
