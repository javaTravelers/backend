package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OffersSearch {
	private String currencyCode;
	private List<OriginDestination> originDestinations;
	private List<Traveler> travelers;
	private final String[] sources = {"GDS"};
	private SearchCriteria searchCriteria; 
}
