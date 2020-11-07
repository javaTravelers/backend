package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightFilters {
	private List<CabinRestriction> cabinRestrictions;
}
