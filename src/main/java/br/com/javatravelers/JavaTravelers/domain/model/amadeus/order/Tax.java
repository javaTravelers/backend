package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Tax {
	
	@ApiModelProperty(value = "Valor", required = true, example = "430")
	private String amount;
	
	@ApiModelProperty(value = "Código", required = true, example = "BR")
	private String code;
}
