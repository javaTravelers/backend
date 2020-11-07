package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact{
    private String emailAddress;
    private List<Phone> phones;
}
