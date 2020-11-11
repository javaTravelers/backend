package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Price;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.PricingOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class FlightOffer{
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-offer")
	private String type;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private String id;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "GDS")
	private String source;
	
	@ApiModelProperty(value = "Necessário ticket", required = false, example = "false")
	private boolean instantTicketingRequired;
	
	@ApiModelProperty(value = "Busca não homogênea", required = false, example = "false")
	private boolean nonHomogeneous;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private boolean paymentCardRequired;
	
	@ApiModelProperty(value = "ID do Segmento", required = false, example = "false")
	private boolean oneWay;
	
	@ApiModelProperty(value = "", required = true, example = "2020-12-04")
	private String lastTicketingDate;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private int numberOfBookableSeats;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private List<Itinerary> itineraries;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private Price price;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private PricingOptions pricingOptions;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private List<String> validatingAirlineCodes;
	
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private List<TravelerPricing> travelerPricings;
}
