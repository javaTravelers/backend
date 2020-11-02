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

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.FavoritesModel;
import br.com.javatravelers.JavaTravelers.domain.repository.FavoriteRepository;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable(value="id") Integer id) {
		
		if (!favoriteRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	//TODO: Realizar chamada para o FavoriteService e implementar o mesmo, verificar como validar se o favorito já existe.
	@PostMapping
	public ResponseEntity<FavoritesModel> saveFavorite(@Valid @RequestBody FavoritesModel favoriteModel) {
		
		try {
			
			
		} catch (BusinnesException e) {
			
		}
		return null;
	}
	
	@GetMapping("/{companyName}/{user_id}")
	public ResponseEntity<List<FavoritesModel>> listByCompany(@PathVariable(value="companyName") String company, @PathVariable(value="user_id") Integer id) {
		
		List<FavoritesModel> favoriteList = favoriteRepository.findByCompanhiaAndUser_Id(company, id);
		
		if(favoriteList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} else {
			return new ResponseEntity<List<FavoritesModel>>(favoriteList, HttpStatus.OK);
		}
	}
	
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
