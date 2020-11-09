package br.com.javatravelers.JavaTravelers.domain.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
	
	
	@ApiModelProperty(value = "Senha do usuario", required = true, example = "user@123.com")
	private String usuario;
	@ApiModelProperty(value = "Nome do usuario", required = true, example = "user")
	private String senha;
	
}
