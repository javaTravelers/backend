package br.com.javatravelers.JavaTravelers.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.domain.service.TicketService;
import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	AmadeusService amadeus;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	
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
	
	@GetMapping("/order/view")
	public ResponseEntity<FlightOrderResult> viewOrder(@RequestParam(value = "flightOrderId") String flightOrderId){
		try {
			flightOrderId = URLEncoder.encode(flightOrderId, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
		FlightOrderResult response = amadeus.viewOrder(flightOrderId);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("/order")
	public ResponseEntity<Boolean> deleteOrder(@RequestParam(value = "flightOrderId") String flightOrderId){
		try {
			flightOrderId = URLEncoder.encode(flightOrderId, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
		boolean response = amadeus.deleteOrder(flightOrderId);
		return  ResponseEntity.status(response ? HttpStatus.OK : HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<TicketModel>> list(){
		List<TicketModel> ticketModel = ticketService.listTickets();		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ticketModel);
	}
	
}
