package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class FlightOfferData{
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-offer")
	private String type;
	@ApiModelProperty(value = "ID", required = true, example = "1")
	private String id;
	@ApiModelProperty(value = "Source", required = true, example = "GDS")
	private String source;
	@ApiModelProperty(value = "Requer Compra Imediata", required = false, example = "false")
	private boolean instantTicketingRequired;
	@ApiModelProperty(value = "Não Homogêneo", required = false, example = "false")
	private boolean nonHomogeneous;
	@ApiModelProperty(value = "Requer Pagamento Com Cartão de Creédito", required = false, example = "false")
	private boolean paymentCardRequired;
	@ApiModelProperty(value = "Viagem Apenas de Ida", required = true, example = "false")
	private boolean oneWay;
	@ApiModelProperty(value = "Data Limite para Compra", required = true, example = "2020-11-12")
	private String lastTicketingDate;
	@ApiModelProperty(value = "Número de Assentos Disponíveis", required = true, example = "9")
	private int numberOfBookableSeats;
	@ApiModelProperty(value = "Itinerários", required = true)
	private List<Itinerary> itineraries;
	@ApiModelProperty(value = "Preço", required = true, example = "Array de Preços")
	private Price price;
	@ApiModelProperty(value = "Opções de Preço", required = true)
	private PricingOptions pricingOptions;
	@ApiModelProperty(required = true, example = "AY")
	private List<String> validatingAirlineCodes;
	@ApiModelProperty(required = true, example = "Array de Preços por Passageiro")
	private List<TravelerPricing> travelerPricings;
}
