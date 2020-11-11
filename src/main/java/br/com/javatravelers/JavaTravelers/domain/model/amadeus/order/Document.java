package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="traveler_document")
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Document{
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty(value = "Tipo de documento", required = true, example = "PASSPORT")
    private String documentType;
	
	@ApiModelProperty(value = "Local de nascimento", required = true, example = "Brasil")
    private String birthPlace;
	
	@ApiModelProperty(value = "Local de emissão do documento", required = true, example = "Brasil")
    private String issuanceLocation;
	
	@ApiModelProperty(value = "Data de emissão do documento", required = true, example = "2015-04-14")
    private String issuanceDate;
    
	@ApiModelProperty(value = "Número do documento", required = true, example = "00000000")
	private String number;
	
	@ApiModelProperty(value = "Data de validade", required = true, example = "2015-04-14")
    private String expiryDate;
	
	@ApiModelProperty(value = "País de emissão do documento", required = true, example = "ES")
    private String issuanceCountry;
	
	@ApiModelProperty(value = "País de validade", required = true, example = "ES")
    private String validityCountry;
	
	@ApiModelProperty(value = "Nacionalidade", required = true, example = "ES")
    private String nationality;
	
	@ApiModelProperty(value = "Data de emissão do documento", required = true, example = "true")
    private boolean holder;
}