package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginDestination {
	
	@ApiModelProperty(hidden = true)
	private int id;
	
	@ApiModelProperty(value = "Cidade de partida (origem)", required = true, example = "NYC")
	private String originLocationCode;
	
	@ApiModelProperty(value = "Cidade de chegada (destino)", required = true, example = "MAD")
	private String destinationLocationCode;
	
	@ApiModelProperty(value = "Data e horário para embarque e desembarque", required = true)
	private DateTimeRange departureDateTimeRange;
	
	@ApiModelProperty(value = "Cidade de chegada (destino)", required = true, example = "MAD")
	private DateTimeRange arrivalDateTimeRange;
}
