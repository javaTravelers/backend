package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Aircraft;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Arrival;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Departure;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class SegmentOffer{
	private Departure departure;
	private Arrival arrival;
	private String carrierCode;
	private String number;
	private Aircraft aircraft;
	private Operating operating;
	private String duration;
	private String id;
	private int numberOfStops;
	private boolean blacklistedInEU;
}
