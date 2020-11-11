package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warning{
    
	@ApiModelProperty(value = "Status", required = true, example = "200")
	private int status;
    
	@ApiModelProperty(value = "Código", required = true, example = "0")
	private int code;
    
	@ApiModelProperty(value = "Título", required = true, example = "Título do aviso")
	private String title;
    
	@ApiModelProperty(value = "Detalhe", required = true, example = "Detalhe do aviso")
	private String detail;
}
