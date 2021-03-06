package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traveler_contact")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact{
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	@ApiModelProperty(value = "Endereço de e-mail", required = true, example = "marcosmonteiro@gmail.com")
	private String emailAddress;
    
	@OneToMany
    private List<Phone> phones;
}
