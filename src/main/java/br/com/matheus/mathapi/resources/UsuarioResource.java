package br.com.matheus.mathapi.resources;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.mathapi.domain.Usuario;
import br.com.matheus.mathapi.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService service;

	@GetMapping()
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> usuario = service.getAllUsuario();
		return ResponseEntity.ok(usuario);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario usuario = service.getById(id);
		return ResponseEntity.ok(usuario);
	}

}
