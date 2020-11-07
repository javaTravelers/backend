package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightOfferGet{
    private String originCode;
    private String destinationCode;
    private String departureDate;
    private String returnDate;
    private Integer adults;
    private Integer children;
    private Integer infants;
    private String currencyCode;
    private Integer max;

	private FlightOfferGet() {
		this.originCode = "";
		this.destinationCode = "";
		this.departureDate = "";
		this.returnDate = "";
		this.adults = 0;
		this.children = 0;
		this.infants = 0;
		this.currencyCode = "";
		this.max = 0;
	}
}
