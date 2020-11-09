package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fee{
	@ApiModelProperty(value = "Valor da Tarifa", required = true, example = "0.00")
	private String amount;
	@ApiModelProperty(value = "Tipo da Tarifa", required = true, example = "SUPPLIER")
	private String type;
}
