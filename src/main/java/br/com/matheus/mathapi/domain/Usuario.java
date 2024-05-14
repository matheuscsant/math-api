package br.com.matheus.mathapi.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity()
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String username;
	@NotBlank
	private String role;

	private Usuario() {
	}

	private Usuario(UsuarioBuilder builder) {
		super();
		this.username = builder.username;
		this.role = builder.role;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public static class UsuarioBuilder {
		private String username;
		private String role;

		public UsuarioBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		public UsuarioBuilder setRole(String role) {
			this.role = role;
			return this;
		}

		public Usuario builder() {
			return new Usuario(this);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(id, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", role=" + role + "]";
	}

}
