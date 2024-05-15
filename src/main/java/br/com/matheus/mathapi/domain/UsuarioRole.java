package br.com.matheus.mathapi.domain;

import java.util.Arrays;

public enum UsuarioRole {

	ADMIN(1), USER(2);

	int role;

	UsuarioRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	public static UsuarioRole valueOf(int codigo) {
		return Arrays.asList(UsuarioRole.values()).stream().filter(role -> role.getRole() == codigo).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid user role code"));
	}

}
