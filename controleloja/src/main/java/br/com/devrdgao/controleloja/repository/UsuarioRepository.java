package br.com.devrdgao.controleloja.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.devrdgao.controleloja.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
		
	Usuario findByLogin(String login);
	
}
