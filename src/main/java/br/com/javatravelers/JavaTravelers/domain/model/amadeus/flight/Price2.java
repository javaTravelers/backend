package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price2{
	private String currency;
	private String total;
	private String base;
	private String grandTotal;
}
