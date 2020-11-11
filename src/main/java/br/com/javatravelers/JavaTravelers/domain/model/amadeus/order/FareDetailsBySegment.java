package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.IncludedCheckedBags;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FareDetailsBySegment{
	
	@ApiModelProperty(value = "Id da classe", required = true, example = "14")
	private String segmentId;
	
	@ApiModelProperty(value = "Classe", required = true, example = "BUSINESS")
	private String cabin;
	
	@ApiModelProperty(value = "Base de tarifa", required = true, example = "JUSEXI6E")
	private String fareBasis;
	
	
	private String brandedFare;
	@JsonProperty("class")
	private String cabinClass;
	
	@ApiModelProperty(value = "Inclui despachar bagagem", required = true, example = "")
	private IncludedCheckedBags includedCheckedBags;
}