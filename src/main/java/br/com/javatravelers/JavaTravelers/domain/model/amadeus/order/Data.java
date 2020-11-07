package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data{
	private String type;
	private List<FlightOffer> flightOffers;
	private List<Traveler> travelers;
}