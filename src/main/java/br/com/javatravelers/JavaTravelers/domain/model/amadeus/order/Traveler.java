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

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "traveler")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Traveler{
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traveler_id;
	
	@ApiModelProperty(value = "Id do viajante", required = true, example = "1")
	private String id;
    
	@ApiModelProperty(value = "Data de nascimento", required = true, example = "1982-01-1")
	private String dateOfBirth;
    
	@ApiModelProperty(value = "Nome do viajante", required = true, example = "Marcos Medeiros")
	@OneToOne
    private Name name;
    
	@ApiModelProperty(value = "Gênero", required = true, example = "MALE")
	private String gender;
    @OneToOne
    
    @ApiModelProperty(value = "Moeda corrente", required = true, example = "BRL")
    private Contact contact;
    
    @ApiModelProperty(value = "Moeda corrente", required = true, example = "BRL")
    @OneToMany
    private List<Document> documents;
}










