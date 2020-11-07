package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginDestination {
	private int id;
	private String originLocationCode;
	private String destinationLocationCode;
	private DateTimeRange departureDateTimeRange;
	private DateTimeRange arrivalDateTimeRange;
}
