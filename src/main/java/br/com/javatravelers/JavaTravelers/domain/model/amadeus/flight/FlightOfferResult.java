package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightOfferResult {
	@ApiModelProperty(value = "Array de Ofertas de Passagens", required = true)
	private List<FlightOfferData> data;
	@ApiModelProperty(value = "Dicionário de Cödigos", required = false)
	private Object dictionaries;
}
