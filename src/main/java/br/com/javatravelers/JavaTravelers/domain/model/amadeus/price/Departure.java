package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Departure{
	@ApiModelProperty(value = "Código iata", required = true, example = "JFK")
	private String iataCode;
	@ApiModelProperty(value = "Terminal", required = true, example = "7")
	private String terminal;
	@ApiModelProperty(value = "Data", required = true, example = "2021-01-10T17:00:00")
	private String at;
}
