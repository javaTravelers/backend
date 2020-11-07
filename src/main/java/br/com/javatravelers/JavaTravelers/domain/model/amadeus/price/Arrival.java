package br.com.javatravelers.JavaTravelers.domain.model.amadeus.price;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Arrival{
	private String iataCode;
	private String terminal;
	private String at;
}
