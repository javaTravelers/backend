package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FareDetailsBySegment{
	
	@ApiModelProperty(value = "ID do segmento", required = true, example = "1")
	private String segmentId;
	
	@ApiModelProperty(value = "Tipo da classe", required = true, example = "ECONOMY")
	private String cabin;
	
	@ApiModelProperty(value = "ID base", required = false, example = "JUSEXI6E")
	private String fareBasis;
	
	
	@ApiModelProperty(value = "Nome da classe", required = false, example = "T")
	@SerializedName("class")
	@JsonProperty("class")
	private String cabinClass;
	
	private IncludedCheckedBags includedCheckedBags;
}