package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Fee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price{
	
	@ApiModelProperty(value = "Moeda", required = true, example = "BRL")
	private String currency;
	
	@ApiModelProperty(value = "Subtotal", required = true, example = "2868.48")
	private String total;
	
	@ApiModelProperty(value = "Valor Base", required = true, example = "17.31")
	private String base;
	
	@ApiModelProperty(value = "Tarifas", required = true, example = "Array de Tarifas")
	private List<Fee> fees;
	
	@ApiModelProperty(value = "Valor Total", required = true, example = "2868.48")
	private String grandTotal;
	
	@ApiModelProperty(value = "Moeda de faturamento", required = false, example = "BRL")
	private String billingCurrency;
}