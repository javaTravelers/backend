package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warning{
    private int status;
    private int code;
    private String title;
    private String detail;
}
