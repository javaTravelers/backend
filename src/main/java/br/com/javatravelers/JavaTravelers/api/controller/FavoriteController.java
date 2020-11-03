package br.com.javatravelers.JavaTravelers.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.javatravelers.JavaTravelers.domain.model.FavoritesModel;
import br.com.javatravelers.JavaTravelers.domain.repository.FavoriteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Favoritos")
@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@ApiOperation(value = "Excluir um favorito")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable(value="id") Integer id) {
		
		if (!favoriteRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	@ApiOperation(value = "Salvar um favorito")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo favorito criado com resultado.")
	})
	@PostMapping(consumes = "apllication/json", produces = "application/json")
	public ResponseEntity<FavoritesModel> saveFavorite(@Valid @RequestBody FavoritesModel favoriteModel) {
		
		return new ResponseEntity<FavoritesModel>(favoriteRepository.save(favoriteModel), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Listar os favoritos por companhia aérea")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida"),
			@ApiResponse(code = 404, message = "Não encontrado")
	})
	@GetMapping("/{companyName}/{user_id}")
	public ResponseEntity<List<FavoritesModel>> listByCompany(@PathVariable(value="companyName") String company, @PathVariable(value="user_id") Integer id) {
		
		List<FavoritesModel> favoriteList = favoriteRepository.findByCompanhiaAndUser_Id(company, id);
		
		if(favoriteList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			return new ResponseEntity<List<FavoritesModel>>(favoriteList, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Listar os favoritos por cidade de origem")
	@GetMapping("/{origin}/{user_id}")
	public ResponseEntity<List<FavoritesModel>> listByOrigem(@PathVariable (value="origin") String company, @PathVariable(value="user_id") Integer id) {
		
		List<FavoritesModel> favoriteList = favoriteRepository.findByCidadeOrigemAndUser_Id(company, id);
		
		if(favoriteList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			return new ResponseEntity<List<FavoritesModel>>(favoriteList, HttpStatus.OK);
		}
	}
	
}
