package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Traveler {
	private String id;
	private String travelerType; // [ ADULT, CHILD, SENIOR, YOUNG, HELD_INFANT, SEATED_INFANT, STUDENT ]
	private String associatedAdultId; // if type="HELD_INFANT", corresponds to the adult travelers's id who will share the seat
}
