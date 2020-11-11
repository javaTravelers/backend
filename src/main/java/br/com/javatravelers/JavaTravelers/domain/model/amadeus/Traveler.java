package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Traveler {
	
	@ApiModelProperty(value = "Id do viajante", required = true, example = "1")
	private String id;
	
	@ApiModelProperty(value = "Tipo de viajante", required = true, example = "ADULT")
	private String travelerType; // [ ADULT, CHILD, SENIOR, YOUNG, HELD_INFANT, SEATED_INFANT, STUDENT ]
	
	@ApiModelProperty(value = "Responsável pela criança de colo", required = false, example = "false")
	private String associatedAdultId; // if type="HELD_INFANT", corresponds to the adult travelers's id who will share the seat
}
