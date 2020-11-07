package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price{
	private String currency;
	private String total;
	private String base;
	private List<Fee> fees;
	private String grandTotal;
}