package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FareDetailsBySegment{
	private String segmentId;
	private String cabin;
	private String fareBasis;
	@SerializedName("class")
	@JsonProperty("class")
	private String cabinClass;
	private IncludedCheckedBags includedCheckedBags;
}