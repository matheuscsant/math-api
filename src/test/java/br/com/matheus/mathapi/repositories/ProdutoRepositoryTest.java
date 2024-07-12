package br.com.matheus.mathapi.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.matheus.mathapi.domain.Produto;

@DataJpaTest
class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repository;

	private Produto produto0;
	private Produto savedProduto;

	@BeforeEach
	public void setup() {
		// Given / Arrange
		produto0 = new Produto(1L, "Produto teste 123");
		savedProduto = repository.save(produto0);
	}

	@DisplayName("Given product object when find by id then return product object")
	@Test
	void testGivenProductObject_whenFindById_thenReturnProductObject() {

		// When / Act
		Produto findProduct = repository.findById(savedProduto.getId()).orElse(null);

		// Then / Assert
		assertNotNull(findProduct);
		assertEquals(findProduct.getId(), findProduct.getId());
		assertTrue(findProduct.getId() > 0);

	}

	@DisplayName("Given product object when update product then return updated product object")
	@Test
	void testGivenProductObject_whenUpdateProduct_thenReturnUpdatedProductObject() {

		// When / Act
		savedProduto.setName("Produto teste 1234");
		Produto updatedProduto = repository.save(savedProduto);

		// Then / Assert
		assertNotNull(updatedProduto);
		assertEquals(updatedProduto.getId(), updatedProduto.getId());
		assertEquals(updatedProduto.getName(), "Produto teste 1234");

	}

	@DisplayName("Given product object when save then return saved product")
	@Test
	void testGivenProductObject_whenSave_thenReturnProductPerson() {
		// When / Act
		Produto savedProduct = repository.save(produto0);

		// Then / Assert
		assertNotNull(savedProduct);
		assertTrue(savedProduct.getId() > 0);
	}

	@DisplayName("Given product list when find all then return product list")
	@Test
	void testGivenProductList_whenFindAll_thenReturnProductList() {
		// Given / Arrange
		Produto produto1 = new Produto(2L, "Produto");

		repository.save(produto0);
		repository.save(produto1);

		// When / Act
		List<Produto> productList = repository.findAll();

		// Then / Assert
		assertNotNull(productList);
		assertEquals(productList.size(), 2);
	}

	@DisplayName("Given product object when delete product then remove product")
	@Test
	void testGivenProductObject_whenDeleteProduct_thenRemoveProduct() {

		// When / Act
		repository.deleteById(savedProduto.getId());
		Optional<Produto> optionalProduct = repository.findById(savedProduto.getId());

		// Then / Assert
		assertTrue(optionalProduct.isEmpty());
	}

}
