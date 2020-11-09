package br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightOfferGet{

	@ApiModelProperty(value = "Origem", required = true, example = "NYC")
    private String originCode;
	@ApiModelProperty(value = "Destino", required = true, example = "MAD")
    private String destinationCode;
	@ApiModelProperty(value = "Data de Ida", required = true, example = "2021-01-10")
    private String departureDate;
	@ApiModelProperty(value = "Data de Retorno", required = true, example = "2021-01-20")
    private String returnDate;
	@ApiModelProperty(value = "Número de Adultos", required = true, example = "1")
    private Integer adults;
	@ApiModelProperty(value = "Número de Crianças Sentadas", required = false, example = "0")
    private Integer children;
	@ApiModelProperty(value = "Número de Crianças de Colo", required = false, example = "0")
    private Integer infants;
	@ApiModelProperty(value = "Moeda de Pagamento", required = true, example = "BRL")
    private String currencyCode;
	@ApiModelProperty(value = "Número Máximo de Resultados", required = true, example = "5")
    private Integer max;

	private FlightOfferGet() {
		this.originCode = "";
		this.destinationCode = "";
		this.departureDate = "";
		this.returnDate = "";
		this.adults = 0;
		this.children = 0;
		this.infants = 0;
		this.currencyCode = "";
		this.max = 0;
	}
}
