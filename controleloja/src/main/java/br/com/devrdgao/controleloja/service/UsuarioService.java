package br.com.devrdgao.controleloja.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.devrdgao.controleloja.models.Usuario;
import br.com.devrdgao.controleloja.repository.UsuarioRepository;

@Repository
@Transactional
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuariorepo;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuariorepo.findByLogin(login);
	    
		if(usuario == null) {	
			throw new UsernameNotFoundException("Usuario não encontrado!");	
		}
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
	}
	
	

}
