package br.com.matheus.mathapi.domain;

import java.util.Arrays;

public enum UsuarioRole {

	ADMIN("ADMIN"), USER("USER");

	String role;

	UsuarioRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public static UsuarioRole valueOfUsuario(String otherRole) {
		return Arrays.asList(UsuarioRole.values()).stream().filter(role -> role.getRole().equalsIgnoreCase(otherRole))
				.findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid user role"));
	}

}
