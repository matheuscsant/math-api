package br.com.matheus.mathapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.mathapi.domain.Produto;
import br.com.matheus.mathapi.repositories.ProdutoRepository;
import br.com.matheus.mathapi.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public List<Produto> getAllProduto() {
		return repository.findAll();
	}

	public Produto getById(Long id) {
		Produto produto = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return produto;
	}

	public Produto createProduto(Produto produto) {
		Produto createdProduto = repository.save(produto);
		return createdProduto;
	}
}
