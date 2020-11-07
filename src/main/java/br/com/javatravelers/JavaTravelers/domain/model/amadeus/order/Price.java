package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Fee;
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
	private String billingCurrency;
}