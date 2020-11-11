package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price{
	
	@ApiModelProperty(value = "Moeda", required = false, example = "BRL")
	private String currency;
	
	@ApiModelProperty(value = "Valor unitário", required = false, example = "1000.00")
	private String total;
	
	@ApiModelProperty(value = "valor base", required = false, example = "500.00")
	private String base;
	
	private List<Fee> fees;
	
	@ApiModelProperty(value = "Valor total", required = false, example = "2000.00")
	private String grandTotal;
}