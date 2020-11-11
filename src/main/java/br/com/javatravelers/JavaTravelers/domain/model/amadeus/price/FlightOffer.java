package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class FlightOffer{
	
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-offer")
	private String type;
	
	@ApiModelProperty(value = "ID", required = false, example = "1")
	private String id;
	
	@ApiModelProperty(value = "Source", required = true, example = "GDS")
	private String source;
	
	@ApiModelProperty(hidden = true)
	private boolean instantTicketingRequired;
	
	@ApiModelProperty(value = "ID", required = false, example = "2020-11-12")
	private boolean nonHomogeneous;
	
	@ApiModelProperty(hidden = true)
	private boolean paymentCardRequired;
	
	@ApiModelProperty(value = "Passagem somente de ida", required = false, example = "false")
	private boolean oneWay;
	
	@ApiModelProperty(value = "Passagem disponíveis até essa data", required = false, example = "2020-11-12")
	private String lastTicketingDate;
	
	@ApiModelProperty(value = "Número de assentos disponíveis", required = true, example = "8")
	private int numberOfBookableSeats;
	
	private List<Itinerary> itineraries;
	
	private Price price;
	
	private PricingOptions pricingOptions;
	
	@ApiModelProperty(value = "Validação dos códigos das companhias", required = false, example = "TP")
	private List<String> validatingAirlineCodes;
	
	
	private List<TravelerPricing> travelerPricings;
}
