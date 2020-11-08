package br.com.javatravelers.JavaTravelers.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.domain.model.FavoriteModel;
import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.domain.status.TicketStatus;
import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;
import br.com.javatravelers.JavaTravelers.service.pagseguro.PaymentResult;
import br.com.javatravelers.JavaTravelers.service.pagseguro.PaymentService;
import br.com.javatravelers.JavaTravelers.service.pagseguro.Payment_items;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	AmadeusService amadeus;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@PostMapping("/search/location")
	public ResponseEntity<String> getLocations(@RequestBody SearchLocation request){
		System.out.println(request.getLocation());
		String response = amadeus.searchLocations(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/offers")
	public ResponseEntity<FlightOfferResult> listar(@RequestBody FlightOfferGet request){
		FlightOfferResult response = amadeus.flightOffers(request);
		return  new ResponseEntity<FlightOfferResult>(response, HttpStatus.OK);
	}
	
	@PostMapping("/offers/search")
	public ResponseEntity<FlightOfferResult> listar(@RequestBody OffersSearch offersSearch){
		FlightOfferResult response = amadeus.flightOffers(offersSearch);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/offers/price")
	public ResponseEntity<FlightPriceResult> flightOffersPrice(@RequestBody FlightPriceSearch offersSearch){
		FlightPriceResult response = amadeus.searchPrice(offersSearch);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/order/create")
	public ResponseEntity<FlightOrderResult> createOrder(@RequestBody FlightOrderGet request){
		FlightOrderResult response = amadeus.createOrder(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/order/view/{flightOrderId}")
	public ResponseEntity<FlightOrderResult> viewOrder(@PathVariable String flightOrderId){
		FlightOrderResult response = amadeus.viewOrder(flightOrderId);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("/order/{flightOrderId}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable String flightOrderId){
		boolean response = amadeus.deleteOrder(flightOrderId);
		return  ResponseEntity.status(response ? HttpStatus.OK : HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@ApiOperation(value = "Gerar ticket passagem")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo ticket criado com sucesso.")
	})
	@PostMapping("/order/create/{user_id}")
	public ResponseEntity<TicketModel> createTicket(@PathVariable(value="user_id") Integer id, @RequestBody FlightOrderGet flight) {
		
		Payment_items payment = new Payment_items();
		payment.setAmount(flight.getData().getFlightOffers().get(0).getPrice().getGrandTotal());
		payment.setDescription("Compra de Passagem javaTravelers");
		payment.setId(1);
		payment.setQuantity(1);
		
		PaymentResult result = paymentService.generatedUrlToCheckout(payment);
				
		TicketModel ticketModel = amadeus.ticketConverter(flight, id);
		
		ticketModel.setPaymentUrl(result.getUrl());
		ticketModel.setPaymentId(result.getCode());
		ticketModel.setStatus("RESERVADO");
		
		return new ResponseEntity<TicketModel>(ticketRepository.save(ticketModel), HttpStatus.CREATED);
	}
	
	
}
