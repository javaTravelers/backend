package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.SegmentOffer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Itinerary {
	@ApiModelProperty(value = "Duração Total da Viagem", required = false, example = "PT7H5M")
	private String duration;
	@ApiModelProperty(required = false, example = "Array de Trechos da Viagem")
	private List<SegmentOffer> segments;
}
