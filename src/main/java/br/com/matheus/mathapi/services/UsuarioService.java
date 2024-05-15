package br.com.matheus.mathapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.matheus.mathapi.domain.Usuario;
import br.com.matheus.mathapi.repositories.UsuarioRepository;
import br.com.matheus.mathapi.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}
	
	public List<Usuario> getAllUsuario() {
		return repository.findAll();
	}

	public Usuario getById(Long id) {
		Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)); 
		return usuario;
	}
	
}
