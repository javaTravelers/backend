package br.com.javatravelers.JavaTravelers.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.domain.enums.TicketStatus;
import br.com.javatravelers.JavaTravelers.domain.model.PaymentModel;
import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.domain.service.TicketService;
import br.com.javatravelers.JavaTravelers.service.pagseguro.NotificationResponse;
import br.com.javatravelers.JavaTravelers.service.pagseguro.PaymentResult;
import br.com.javatravelers.JavaTravelers.service.pagseguro.PaymentService;
import br.com.javatravelers.JavaTravelers.service.pagseguro.Payment_items;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@PostMapping
	public ResponseEntity<PaymentResult> payWithCheckout(@RequestBody Payment_items items) {
		return new ResponseEntity<PaymentResult>(paymentService.generatedUrlToCheckout(items), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Gerar o ticket pré-reserva e o código de pagamento.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida. Compra de passagem concluída.", response = TicketModel.class),
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Status do Pagamento Atualizado.", response = PaymentModel.class),
			@ApiResponse(code = 404, message = "Pagamento não localizado")
	})
	
	@PostMapping("/create")
	public ResponseEntity<PaymentModel> createTicket(@RequestBody FlightOrderGet flight) {
		
		Payment_items payment = new Payment_items();
		payment.setAmount(flight.getData().getFlightOffers().get(0).getPrice().getGrandTotal());
		payment.setDescription("Compra de Passagem javaTravelers");
		payment.setId(1);
		payment.setQuantity(1);
		
		PaymentResult result = paymentService.generatedUrlToCheckout(payment);
				
		TicketModel ticketModel = ticketService.ticketConverter(flight);
		
		ticketModel.setPaymentUrl(result.getUrl());
		ticketModel.setPaymentId(result.getCode());
		ticketModel.setPrice(Double.parseDouble(payment.getAmount()));
		ticketModel.setStatus(TicketStatus.RESERVADO);
		ticketRepository.save(ticketModel);
		
		return new ResponseEntity<PaymentModel>(paymentService.create(ticketModel), HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{ticketCode}")
	public ResponseEntity<TicketModel> getTicket(@PathVariable String ticketCode){
		TicketModel ticketModel = ticketService.getTicket(ticketCode);		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ticketModel);
	}
	
	@PostMapping("/order/response")
	public ResponseEntity<?> getResponse(@RequestBody NotificationResponse notification){
		Object response = paymentService.updateStatus(notification);
		
		if (response == null) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(null);
		}
		else if (response.getClass() == TicketModel.class) {
			return  ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response);
		}else {
			return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PaymentModel>> list(){
		List<PaymentModel> paymentmodel = paymentService.listPayments();		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(paymentmodel);
	}
	
}
