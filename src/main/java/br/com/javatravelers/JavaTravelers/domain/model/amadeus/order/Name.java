package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traveler_name")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name{
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	@ApiModelProperty(value = "Nome do viajante", required = true, example = "Marcos")
	private String firstName;
    
	@ApiModelProperty(value = "Sobrenome", required = true, example = "Monteiro")
	private String lastName;
}
