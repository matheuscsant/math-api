package br.com.matheus.mathapi.resources;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.mathapi.domain.Produto;
import br.com.matheus.mathapi.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		List<Produto> produtos = service.getAllProduto();
		return ResponseEntity.ok(produtos);
	}

	@PostMapping
	public ResponseEntity<Object> createAllProducts(@RequestBody List<Produto> produtos) {
		service.createProducts(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteAllProducts() {
		service.deleteAllProducts();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
