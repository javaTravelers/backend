package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Aircraft{
	@ApiModelProperty(value = "Código da Aeronave", required = true, example = "333")
	private String code;
}
