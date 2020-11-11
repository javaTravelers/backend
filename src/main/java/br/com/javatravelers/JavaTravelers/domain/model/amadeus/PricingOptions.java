package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingOptions {
	
	@ApiModelProperty(value = "Despachar bagagem", required = false, example = "false")
	private boolean includedCheckedBagsOnly;
}
