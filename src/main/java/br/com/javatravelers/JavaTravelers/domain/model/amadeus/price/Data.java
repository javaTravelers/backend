package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{
	private String type;
	private List<FlightOffer> flightOffers;
}