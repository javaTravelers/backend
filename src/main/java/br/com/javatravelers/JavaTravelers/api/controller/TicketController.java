package br.com.javatravelers.JavaTravelers.api.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.domain.service.TicketService;
import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	
	@Autowired
	AmadeusService amadeus;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	TicketService ticketService;
	
	@ApiOperation(value = "Consulta de Cidades, Países ou Aeroportos.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Retorna um Array de Locais", response = ArrayList.class),
			@ApiResponse(code = 404, message = "Não foi possível localizar um local com os parâmetros."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
	@PostMapping("/search/location")
	public ResponseEntity<String> getLocations(@RequestBody SearchLocation request){
		String response = amadeus.searchLocations(request);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@ApiOperation(value = "Consulta de Vôos Básica")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Retorna um Array de Vôos.", response = FlightOfferResult[].class),
			@ApiResponse(code = 404, message = "Não foi possível localizar um local com os parâmetros."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
	@PostMapping("/offers")
	public ResponseEntity<FlightOfferResult> listar(@RequestBody FlightOfferGet request){
		FlightOfferResult response = amadeus.flightOffers(request);
		return  new ResponseEntity<FlightOfferResult>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Consulta de Vôos Avançada")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Retorna um Array de Vôos", response = FlightOfferResult[].class),
			@ApiResponse(code = 404, message = "Não foi possível localizar vôos com os parâmetros informados."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
	@PostMapping("/offers/search")
	public ResponseEntity<FlightOfferResult> listar(@RequestBody OffersSearch offersSearch){
		FlightOfferResult response = amadeus.flightOffers(offersSearch);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@ApiOperation(value = "Confirmação de Preço da Passagem")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Retorna um Array de Vôos com Preços e Taxas", response = FlightPriceResult[].class),
			@ApiResponse(code = 404, message = "Não foi possível localizar vôos com os parâmetros informados."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
	@PostMapping("/offers/price")
	public ResponseEntity<FlightPriceResult> flightOffersPrice(@RequestBody FlightPriceSearch offersSearch){
		FlightPriceResult response = amadeus.searchPrice(offersSearch);
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@ApiOperation(value = "Consultar status do vôo.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Retorna os Dados do Vôo Atualizados", response = FlightOrderResult.class),
			@ApiResponse(code = 404, message = "Não foi possível localizar o vôo informado."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
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
	
	@ApiOperation(value = "Cancelar uma reserva após a compra.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. A reserva foi excluída com sucesso."),
			@ApiResponse(code = 404, message = "Não foi possível localizar a reserva informada."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
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
	
	@ApiOperation(value = "Listar Todas Passagens Reservadas ou Emitidas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. A reserva foi excluída com sucesso."),
			@ApiResponse(code = 404, message = "Não foi possível localizar a reserva informada."),
			@ApiResponse(code = 500, message = "Erro interno do servidor. Tente novamente em alguns instantes.")
	})
	@GetMapping("/list")
	public ResponseEntity<List<TicketModel>> list(){
		List<TicketModel> ticketModel = ticketService.listTickets();		
		return  ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(ticketModel);
	}
	
}
