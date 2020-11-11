package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelerPricing{
	
	@ApiModelProperty(value = "Id do viajante", required = true, example = "1")
	private String travelerId;
	
	@ApiModelProperty(value = "Opção de tarifa", required = true, example = "STANDARD")
	private String fareOption;
	
	@ApiModelProperty(value = "Tipo do viajante", required = true, example = "ADULT")
	private String travelerType;
	
	@ApiModelProperty(value = "Preço", required = true, example = "2868.48")
	private Price2 price;
	private List<FareDetailsBySegment> fareDetailsBySegment;
}
