package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone{
    private String deviceType;
    private String countryCallingCode;
    private String number;
}
