package br.com.projeto.crud.loja.seguranca.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.loja.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${crud.loja.jwt.expiration}")
	private String expiracao;
	
	@Value("${crud.loja.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		Usuario usuario = (Usuario) authenticate.getPrincipal();
		Date agora = new Date();
		Date expiracao = new Date(agora.getTime() + Long.parseLong(this.expiracao));
		return  Jwts.builder()
				.setIssuer("API de Loja Portifólio")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(expiracao)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long recuperarIdUsuario(String token) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		Claims claims = claimsJws.getBody();
		String idUsuario = claims.getSubject();
		return Long.parseLong(idUsuario);
	}
}