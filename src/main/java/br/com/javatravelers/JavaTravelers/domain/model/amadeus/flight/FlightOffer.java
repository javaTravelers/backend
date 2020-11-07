package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Price;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.PricingOptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class FlightOffer{
	private String type;
	private String id;
	private String source;
	private boolean instantTicketingRequired;
	private boolean nonHomogeneous;
	private boolean paymentCardRequired;
	private boolean oneWay;
	private String lastTicketingDate;
	private int numberOfBookableSeats;
	private List<Itinerary> itineraries;
	private Price price;
	private PricingOptions pricingOptions;
	private List<String> validatingAirlineCodes;
	private List<TravelerPricing> travelerPricings;
}
