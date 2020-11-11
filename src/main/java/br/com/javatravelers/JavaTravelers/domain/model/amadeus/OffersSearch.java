package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OffersSearch {
	
	@ApiModelProperty(value = "Moeda corrente", required = true, example = "BRL")
	private String currencyCode;
	
	private List<OriginDestination> originDestinations;
	
	private List<Traveler> travelers;
	
	@ApiModelProperty(hidden = true)
	private final String[] sources = {"GDS"};
	
	private SearchCriteria searchCriteria; 
}
