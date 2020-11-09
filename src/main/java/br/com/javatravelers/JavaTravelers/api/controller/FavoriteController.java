package br.com.javatravelers.JavaTravelers.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.javatravelers.JavaTravelers.domain.model.FavoriteModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.FavoriteRepository;
import br.com.javatravelers.JavaTravelers.domain.service.FavoriteService;
import br.com.javatravelers.JavaTravelers.infra.security.service.AuthenticationFacade;
import br.com.javatravelers.JavaTravelers.service.amadeus.AmadeusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Favoritos")
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private FavoriteService fs;
	
	@Autowired
	private AmadeusService as;
	
	@Autowired
	private AuthenticationFacade auth;

	@ApiOperation(value = "Excluir um favorito")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Favorito removido."),
			@ApiResponse(code = 404, message = "Não encontrado.")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteFavorite(@PathVariable(value="id")  Integer  id) {
		
		if (!fs.favoriteDelete(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(false);

		} else {
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(true);
		}
	}


	@ApiOperation(value = "Salvar um favorito")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo favorito criado com resultado.")
	})
	@PostMapping()
	public ResponseEntity<FavoriteModel> createFavorite(@RequestBody FlightPriceSearch flight) {

		FavoriteModel favoriteModel = fs.favoriteConverter(flight);

		return new ResponseEntity<FavoriteModel>(favoriteRepository.save(favoriteModel), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Listar Favorito Por Usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo favorito criado com resultado.")
	})
	@GetMapping("/list")
	public ResponseEntity<List<FlightPriceSearch>> listFavorite() {

		List<FlightPriceSearch> flights = fs.flightConverter();

		return new ResponseEntity<List<FlightPriceSearch>>(flights, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Selecionar Favorito para Compra")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo favorito criado com resultado.")
	})
	@GetMapping("/{favorite_id}")
	public ResponseEntity<FlightPriceResult> get(@PathVariable(value="favorite_id") Integer id) {

		FlightPriceSearch flight = fs.flightConverter(id);
		
		FlightPriceResult result = as.searchPrice(flight);

		return new ResponseEntity<FlightPriceResult>(result, HttpStatus.CREATED);
	}

}
