package br.com.javatravelers.JavaTravelers.domain.model.amadeus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateTimeRange {
	
	@ApiModelProperty(value = "Data", required = true, example = "2021-02-02")
	private String date;
	
	@ApiModelProperty(value = "Janela de dias para mais ou para menos (Inserir valores de 1 a 3)", required = false, example = "2")
	private String dateWindow;
	
	@ApiModelProperty(value = "Hora", required = false, example = "22:05:00")
	private String time;
	
	@ApiModelProperty(value = "Janela de horários (Inserir valores 1 a 12 seguido de 'H')", required = false, example = "12H")
	private String timeWindow;
	
	
}
