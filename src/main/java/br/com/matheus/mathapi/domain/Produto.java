package br.com.matheus.mathapi.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity()
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Double price;

	private Produto() {
	}

	private Produto(ProdutoBuilder builder) {
		super();
		this.name = builder.name;
		this.price = builder.price;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public static class ProdutoBuilder {
		private String name;
		private Double price;

		public ProdutoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ProdutoBuilder setPrice(Double price) {
			this.price = price;
			return this;
		}

		public Produto builder() {
			return new Produto(this);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
