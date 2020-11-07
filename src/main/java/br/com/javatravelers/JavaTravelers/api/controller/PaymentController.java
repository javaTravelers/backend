package br.com.javatravelers.JavaTravelers.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.service.pagseguro.PaymentService;
import br.com.javatravelers.JavaTravelers.service.pagseguro.Payment_items;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<String> payWithCheckout(@RequestBody Payment_items items) {
		return new ResponseEntity<String>(paymentService.generatedUrlToCheckout(items), HttpStatus.OK);
	}
}
