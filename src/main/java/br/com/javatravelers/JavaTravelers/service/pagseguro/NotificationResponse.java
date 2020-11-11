package br.com.javatravelers.JavaTravelers.service.pagseguro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
	
	@ApiModelProperty(value = "Código da transação", required = true, example = "JB94C7E6E9797D8DFF4F8BF8E5E50DFD2")
	private String transactionCode;
	
	@ApiModelProperty(value = "Status da transação", required = true, example = "APROVADO")
	private String status;
}
