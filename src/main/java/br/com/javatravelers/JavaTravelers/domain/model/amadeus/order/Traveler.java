package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Traveler{
    private String id;
    private String dateOfBirth;
    private Name name;
    private String gender;
    private Contact contact;
    private List<Document> documents;
}










