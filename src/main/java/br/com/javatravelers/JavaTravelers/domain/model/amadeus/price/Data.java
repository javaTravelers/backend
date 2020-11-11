package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{
	
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-offers-pricing")
	private String type;
	private List<FlightOffer> flightOffers;
}