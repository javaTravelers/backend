package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinRestriction {
	
	@ApiModelProperty(value = "Classe", required = false, example = "ECONOMY")
	private String cabin; // [ ECONOMY, PREMIUM_ECONOMY, BUSINESS, FIRST ]
	
	@ApiModelProperty(value = "Escolha da classe por trecho", required = false, example = "ALL_SEGMENTS")
	private String coverage; // [ MOST_SEGMENTS, AT_LEAST_ONE_SEGMENT, ALL_SEGMENTS ]
	
	@ApiModelProperty(value = "ID", required = false, example = "6")
	private String[] originDestinationIds;
}
