package br.com.javatravelers.JavaTravelers.domain.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class Login {
	@ApiModelProperty(value = "Nome do usuario", required = true, example = "user")
	private String usuario;
	@ApiModelProperty(value = "Senha do usuario", required = true, example = "user@123")
	private String senha;
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
