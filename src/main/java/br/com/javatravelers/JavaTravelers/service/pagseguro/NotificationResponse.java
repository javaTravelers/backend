package br.com.javatravelers.JavaTravelers.service.pagseguro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
	private String transactionCode;
	private String status;
}
