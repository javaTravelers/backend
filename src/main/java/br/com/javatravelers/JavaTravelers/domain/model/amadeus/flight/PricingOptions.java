package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingOptions{
	private List<String> fareType;
	private boolean includedCheckedBagsOnly;
}
