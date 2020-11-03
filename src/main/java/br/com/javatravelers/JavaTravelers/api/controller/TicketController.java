package br.com.javatravelers.JavaTravelers.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	AmadeusService amadeus;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/search/offers")
	public ResponseEntity<String> listar(@RequestBody Map<String, String> request){
		String response = amadeus.flightOffers(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/search/location")
	public ResponseEntity<String> getLocations(@RequestBody SearchLocation request){
		System.out.println(request.getLocation());
		String response = amadeus.searchLocations(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/confirmPrice")
	public ResponseEntity<String> getPrice(@RequestBody String request){
		String response = amadeus.searchPrice(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	
}
