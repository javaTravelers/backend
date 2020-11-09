package br.com.javatravelers.JavaTravelers.service.pagseguro;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Checkout {

	private List<Payment_items> payment_items;
	private String currency;
	private Receiver receiver;
	private String notificationURL;
}
