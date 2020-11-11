package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price2{
	
	@ApiModelProperty(value = "Moeda", required = true, example = "BRL")
	private String currency;
	
	@ApiModelProperty(value = "Valor unitário", required = true, example = "1000.00")
	private String total;
	
	@ApiModelProperty(value = "Valor base", required = true, example = "500.00")
	private String base;
	
	@ApiModelProperty(value = "Taxas", required = true, example = "BRL")
	private Tax[] taxes;
	
	@ApiModelProperty(hidden = true)
	private String refundableTaxes;
}
