package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.IncludedCheckedBags;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class FareDetailsBySegment{
	@ApiModelProperty(value = "ID do Segmento", required = true, example = "1")
	private String segmentId;
	@ApiModelProperty(value = "Cabine", required = true, example = "ECONOMY")
	private String cabin;
	@ApiModelProperty(value = "fareBasis", required = true, example = "OLN0F0B5")
	private String fareBasis;
	@SerializedName("class")
	@JsonProperty("class")
	@ApiModelProperty(value = "Classe", required = true, example = "O")
	private String cabinClass;
	@ApiModelProperty(value = "Bagagens", required = true, example = "Bagagens")
	private IncludedCheckedBags includedCheckedBags;
}