package br.com.projeto.crud.loja.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.projeto.crud.loja.data.service.UsuarioDataService;
import br.com.projeto.crud.loja.modelo.Usuario;
import br.com.projeto.crud.loja.seguranca.service.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioDataService usuarioService;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioDataService usuarioService) {
		this.tokenService = tokenService; this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean tokenEhvalido = tokenService.validarToken(token);
		if(tokenEhvalido) 
			autenticarCliente(token);
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.recuperarIdUsuario(token);
		Usuario usuario = this.usuarioService.consultarPorId(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String cabecalho = request.getHeader("Authorization");
		if(cabecalho == null || cabecalho.isEmpty() || !(cabecalho.startsWith("Bearer ")))
			return null;
		return cabecalho.substring(7, cabecalho.length());
	}
}