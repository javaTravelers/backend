package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Document{
    private String documentType;
    private String birthPlace;
    private String issuanceLocation;
    private String issuanceDate;
    private String number;
    private String expiryDate;
    private String issuanceCountry;
    private String validityCountry;
    private String nationality;
    private boolean holder;
}