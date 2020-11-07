package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.SegmentOffer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Itinerary {
	private String duration;
	private List<SegmentOffer> segments;
}
