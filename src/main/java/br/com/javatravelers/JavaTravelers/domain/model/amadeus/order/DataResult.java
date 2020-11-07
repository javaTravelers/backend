package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightOffer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResult {
	private String type;
	private String id;
	private List<AssociatedRecord> associatedRecords;
	private List<FlightOffer> flightOffers;
	private List<Traveler> travelers;
}
