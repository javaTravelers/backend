package br.com.javatravelers.JavaTravelers.service.pagseguro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResult {
	
	@ApiModelProperty(value = "URL resultado para acesso ao site checkout de pagamento", required = true, example = "https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=B94C7E6E9797D8DFF4F8BF8E5E50DFD2")
	private String url;
	
	@ApiModelProperty(value = "Código gerado para identificação da transação", required = true, example = "B94C7E6E9797D8DFF4F8BF8E5E50DFD2")
	private String code;
}
