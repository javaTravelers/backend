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
@Table(name = "traveler_phone")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone{
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	@ApiModelProperty(value = "Tipo de telefone", required = true, example = "MOBILE")
	private String deviceType;
    
	@ApiModelProperty(value = "Código de telefone do país", required = true, example = "55")
	private String countryCallingCode;
    
	@ApiModelProperty(value = "Número do telefone", required = true, example = "12991758980")
	private String number;
}
