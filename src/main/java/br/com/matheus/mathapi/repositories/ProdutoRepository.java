package br.com.matheus.mathapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.mathapi.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
