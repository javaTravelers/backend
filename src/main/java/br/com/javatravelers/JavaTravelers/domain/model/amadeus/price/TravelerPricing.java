package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelerPricing{
	
	@ApiModelProperty(value = "ID do viajante", required = true, example = "1")
	private String travelerId;
	
	@ApiModelProperty(value = "Opção de tarifa", required = true, example = "STANDARD")
	private String fareOption;
	
	@ApiModelProperty(value = "Tipo de viajante", required = true, example = "ADULT")
	private String travelerType;
	
	private Price2 price;
	
	@ApiModelProperty(value = "ID", required = true, example = "1")
	private List<FareDetailsBySegment> fareDetailsBySegment;
}
