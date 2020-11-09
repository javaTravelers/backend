package br.com.javatravelers.JavaTravelers.domain.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
	
	
	@ApiModelProperty(value = "Usuário", required = true, example = "marcosmonteiro@gmail.com")
	private String usuario;
	@ApiModelProperty(value = "Senha do Usuário", required = true, example = "marcos123456")
	private String senha;
	
}
