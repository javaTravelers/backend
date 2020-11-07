package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;


import java.util.List;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.Warning;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightPriceResult {
	
	private List<Warning> warnings;
	private Data data;
	private Object dictionaries;
}
