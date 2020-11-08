package br.com.javatravelers.JavaTravelers.service.pagseguro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResult {
	private String url;
	private String code;
}
