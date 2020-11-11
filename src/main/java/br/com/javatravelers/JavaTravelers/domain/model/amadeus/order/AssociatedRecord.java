package br.com.javatravelers.JavaTravelers.domain.model.amadeus.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociatedRecord{
    
	@ApiModelProperty(value = "Referência", required = true, example = "NBN35N")
	private String reference;
    
	@ApiModelProperty(value = "Data de criação", required = true, example = "2020-11-11T04:09:00.000")
	private String creationDate;
    
	@ApiModelProperty(value = "Código de origem", required = true, example = "GDS")
	private String originSystemCode;
    
	@ApiModelProperty(value = "ID", required = true, example = "T")
	private String flightOfferId;
}
