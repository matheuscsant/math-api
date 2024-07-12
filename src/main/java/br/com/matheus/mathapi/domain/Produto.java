package br.com.matheus.mathapi.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private Integer codigoAlternativo;

	private String name;

	@Column(nullable = true)
	private String tabelaDePreco;

	public Produto() {
	}

	public Produto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Produto(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigoAlternativo() {
		return codigoAlternativo;
	}

	public void setCodigoAlternativo(Integer codigoAlternativo) {
		this.codigoAlternativo = codigoAlternativo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTabelaDePreco() {
		return tabelaDePreco;
	}

	public void setTabelaDePreco(String tabelaDePreco) {
		this.tabelaDePreco = tabelaDePreco;
	}

}
