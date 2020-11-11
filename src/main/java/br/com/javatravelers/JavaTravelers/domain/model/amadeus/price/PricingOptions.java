package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingOptions{
	
	@ApiModelProperty(value = "Tipo de tarifa", required = true, example = "PUBLISHED")
	private List<String> fareType;
	
	@ApiModelProperty(value = "Incluir somente com bagagem despachada", required = false, example = "true")
	private boolean includedCheckedBagsOnly;
}
