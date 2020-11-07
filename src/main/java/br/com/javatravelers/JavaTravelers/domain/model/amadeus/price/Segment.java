package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Segment{
	private Departure departure;
	private Arrival arrival;
	private String carrierCode;
	private String number;
	private Aircraft aircraft;
	private String duration;
	private String id;
	private int numberOfStops;
	private boolean blacklistedInEU;
}