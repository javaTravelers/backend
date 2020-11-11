package br.com.javatravelers.JavaTravelers.service.amadeus.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchLocation {
	
	@ApiModelProperty(value = "Localização", required = true, example = "MAD")
	private String location;
}
