package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class IncludedCheckedBags{
	@ApiModelProperty(value = "Quantidade", required = true, example = "0")
	private int quantity;
	@ApiModelProperty(value = "Peso", required = true, example = "0")
	private int weight;
	@ApiModelProperty(value = "Unidade de Peso", required = false, example = "KG")
	private String weightUnit;
}
