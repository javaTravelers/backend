package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingOptions{
	@ApiModelProperty(value = "Array de fareType", required = true, example = "Array de farType")
	private List<String> fareType;
	@ApiModelProperty(value = "Incluir apenas com bagagem despachada", required = true, example = "false")
	private boolean includedCheckedBagsOnly;
}
