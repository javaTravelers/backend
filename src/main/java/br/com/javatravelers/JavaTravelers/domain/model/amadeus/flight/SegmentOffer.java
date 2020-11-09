package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Aircraft;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Arrival;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.Departure;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class SegmentOffer{
	@ApiModelProperty(value = "Embarque", required = true)
	private Departure departure;
	@ApiModelProperty(value = "Desembarque", required = true)
	private Arrival arrival;
	@ApiModelProperty(value = "Código da Aerolínea", required = true, example = "AY")
	private String carrierCode;
	@ApiModelProperty(value = "Número", required = true, example = "5654")
	private String number;
	private Aircraft aircraft;
	private Operating operating;
	@ApiModelProperty(value = "Duração do Trecho", required = true, example = "PT7H5M")
	private String duration;
	@ApiModelProperty(value = "ID do Trecho", required = true, example = "1")
	private String id;
	@ApiModelProperty(value = "Número de Paradas", required = true, example = "0")
	private int numberOfStops;
	@ApiModelProperty(value = "Lista Negra da União Europeia", required = true, example = "false")
	private boolean blacklistedInEU;
}
