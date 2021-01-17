package br.com.projeto.crud.loja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.loja.dto.TokenDto;
import br.com.projeto.crud.loja.form.LoginForm;
import br.com.projeto.crud.loja.seguranca.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm loginForm) {
		UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
