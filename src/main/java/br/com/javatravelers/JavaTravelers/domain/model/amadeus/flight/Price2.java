package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price2{
	@ApiModelProperty(value = "Moeda", required = true, example = "BRL")
	private String currency;
	@ApiModelProperty(value = "Subtotal", required = true, example = "2868.48")
	private String total;
	@ApiModelProperty(value = "Valor Base", required = true, example = "17.31")
	private String base;
	@ApiModelProperty(value = "Valor Total", required = true, example = "2868.48")
	private String grandTotal;
}
