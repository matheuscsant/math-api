package br.com.matheus.mathapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.mathapi.domain.AuthenticationDTO;
import br.com.matheus.mathapi.domain.LoginResponseDTO;
import br.com.matheus.mathapi.domain.RegisterDTO;
import br.com.matheus.mathapi.domain.Usuario;
import br.com.matheus.mathapi.repositories.UsuarioRepository;
import br.com.matheus.mathapi.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO dto) {
		var usernamePassowrd = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = authenticationManager.authenticate(usernamePassowrd);

		var token = tokenService.generateToken(Usuario.class.cast(auth.getPrincipal()));

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
		if (userRepository.findByLogin(dto.login()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassoword = new BCryptPasswordEncoder().encode(dto.password());
		Usuario newUser = Usuario.builder().login(dto.login()).password(encryptedPassoword).role(dto.role().getRole())
				.build();

		userRepository.save(newUser);

		return ResponseEntity.ok().build();
	}

}
