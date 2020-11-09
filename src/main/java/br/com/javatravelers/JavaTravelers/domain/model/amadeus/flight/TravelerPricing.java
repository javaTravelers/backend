package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelerPricing{
	@ApiModelProperty(value = "Passageiro nº", required = true, example = "1")
	private String travelerId;
	@ApiModelProperty(value = "fareOption", required = true, example = "STANDARD")
	private String fareOption;
	@ApiModelProperty(value = "Tipo de Passageiro : [ ADULT, CHILD, HELD_INFANT ]", required = true, example = "ADULT")
	private String travelerType;
	@ApiModelProperty(value = "Preço", required = true)
	private Price2 price;
	@ApiModelProperty(value = "FareDetails por Segmento", required = true)
	private List<FareDetailsBySegment> fareDetailsBySegment;
}
