package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.IncludedCheckedBags;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FareDetailsBySegment{
	private String segmentId;
	private String cabin;
	private String fareBasis;
	private String brandedFare;
	@JsonProperty("class")
	private String cabinClass;
	private IncludedCheckedBags includedCheckedBags;
}