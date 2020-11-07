package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociatedRecord{
    private String reference;
    private String creationDate;
    private String originSystemCode;
    private String flightOfferId;
}
