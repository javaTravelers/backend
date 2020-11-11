package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price2{
	
	@ApiModelProperty(value = "Moeda", required = true, example = "BRL")
	private String currency;
	
	@ApiModelProperty(value = "Valor unitário", required = true, example = "1000.00")
	private double total;
	
	@ApiModelProperty(value = "Valor base", required = true, example = "500.00")
	private double base;
	
	@ApiModelProperty(value = "Valor total", required = true, example = "2000.00")
	private double grandTotal;
}
