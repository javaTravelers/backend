package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
	
	@ApiModelProperty(value = "Passagem somente de ida", required = true, example = "false")
	private boolean addOneWayOffers;
	
	@ApiModelProperty(value = "Quantidade máxima de ofertas por busca", required = false, example = "4")
	private Integer maxFlightOffers;
	
	@ApiModelProperty(value = "Preço total máximo da passagem", required = false, example = "1000")
	private Integer maxPrice;
	
	private PricingOptions pricingOptions;
	
	@ApiModelProperty(value = "Escolha da classe", required = true, example = "ECONOMY")
	private FlightFilters flightFilters;
}
