package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{
	
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-order")
	private String type;
	private List<FlightOffer> flightOffers;
	private List<Traveler> travelers;
}