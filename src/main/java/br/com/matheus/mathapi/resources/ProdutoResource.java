package br.com.matheus.mathapi.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.matheus.mathapi.domain.Produto;
import br.com.matheus.mathapi.services.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoService service;

	@GetMapping()
	public ResponseEntity<List<Produto>> getAll() {
		List<Produto> produtos = service.getAllProduto();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto produto = service.getById(id);
		return ResponseEntity.ok(produto);
	}

	@PostMapping()
	public ResponseEntity<Object> createProduto(@RequestBody @Valid Produto produto, BindingResult validation) {

		if (validation.hasErrors())
			return ResponseEntity.badRequest().body(validation.getAllErrors());

		Produto createdProduto = service.createProduto(produto);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
				.buildAndExpand(createdProduto.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
