package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightOffer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResult {
	
	@ApiModelProperty(value = "Tipo", required = true, example = "flight-order")
	private String type;
	
	@ApiModelProperty(value = "ID", required = true, example = "eJzTd9f3c%2FIzNvUDAArYAjo%3D")
	private String id;
	private List<AssociatedRecord> associatedRecords;
	private List<FlightOffer> flightOffers;
	private List<Traveler> travelers;
}
