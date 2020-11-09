package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Operating {
	@ApiModelProperty(value = "Código da Operadora", required = true, example = "IB")
	private String carrierCode;
}
