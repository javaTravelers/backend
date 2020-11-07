package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	private boolean addOneWayOffers;
	private Integer maxFlightOffers;
	private Integer maxPrice;
	private PricingOptions pricingOptions;
	private FlightFilters flightFilters;
}
