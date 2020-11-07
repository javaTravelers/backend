package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Itinerary {
	private List<SegmentOffer> segments;
}
