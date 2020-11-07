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

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	AmadeusService amadeus;
	
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
	
	@DeleteMapping("/order/delete/{flightOrderId}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable String flightOrderId){
		boolean response = amadeus.deleteOrder(flightOrderId);
		return  ResponseEntity.status(response ? HttpStatus.OK : HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	
}
