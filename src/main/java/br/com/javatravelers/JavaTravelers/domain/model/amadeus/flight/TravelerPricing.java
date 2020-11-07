package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TravelerPricing{
	private String travelerId;
	private String fareOption;
	private String travelerType;
	private Price2 price;
	private List<FareDetailsBySegment> fareDetailsBySegment;
}
