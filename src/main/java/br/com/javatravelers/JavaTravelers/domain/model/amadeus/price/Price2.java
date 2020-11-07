package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price2{
	private String currency;
	private double total;
	private double base;
	private double grandTotal;
}
