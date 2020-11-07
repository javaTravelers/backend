package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traveler")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Traveler{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traveler_id;
	private String id;
    private String dateOfBirth;
    @OneToOne
    private Name name;
    private String gender;
    @OneToOne
    private Contact contact;
    @OneToMany
    private List<Document> documents;
}










