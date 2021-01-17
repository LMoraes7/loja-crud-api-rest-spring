package br.com.projeto.crud.loja.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank(message = "o campo username não pode ser vazio")
	private String username;
	@NotBlank(message = "O campo senha não pode ser vazio")
	private String senha;

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getSenha() {return senha;}

	public void setSenha(String senha) {this.senha = senha;}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.username, this.senha);
	}
}
