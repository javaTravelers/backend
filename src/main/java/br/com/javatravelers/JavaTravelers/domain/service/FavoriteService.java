package br.com.javatravelers.JavaTravelers.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.FavoriteModel;
import br.com.javatravelers.JavaTravelers.domain.model.UserModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.FavoriteRepository;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;
import br.com.javatravelers.JavaTravelers.uteis.ValidaCPF;

@Service
public class FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;

	@Autowired
	private UserRepository userRepository;

	public FavoriteModel saveUser(FavoriteModel favoriteModel) throws BusinnesException{

		/*
		 * FavoriteModel favoriteModelDB =
		 * userRepository.findByEmail(favoriteModel.getEmail());
		 * 
		 * if ((favoriteModelDB != null) && (!favoriteModelDB.equals(favoriteModel))) {
		 * throw new
		 * BusinnesException("Já existe um usuário cadastrado com esse e-mail."); }
		 */

		return favoriteRepository.save(favoriteModel);
	}

	public FavoriteModel favoriteConverter(FlightPriceSearch flight, int id) {
		UserModel user = userRepository.findById(id);

		if (user == null) {
			throw new BusinnesException("Usuário não localizado: ID: "+ id);
		}

		Gson json = new Gson();
		String strFlight = json.toJson(flight);

		FavoriteModel favoriteModel = new FavoriteModel();

		favoriteModel.setFlight(strFlight);
		favoriteModel.setUserId(user.getId());

		return favoriteModel;
	}

	public List<FlightPriceSearch> flightConverter(int id) {
		List<FavoriteModel>  favorites = favoriteRepository.findAllByUserId(id);

		if (favorites.size() <= 0) {
			throw new BusinnesException("Não encontramos favoritos para o usuário: "+ id);
		}

		List<FlightPriceSearch> flights = new ArrayList<FlightPriceSearch>(); 
		Gson json = new Gson();
		for (int i = 0; i < favorites.size(); i++) {
			flights.add(json.fromJson(favorites.get(i).getFlight(), FlightPriceSearch.class));
		}

		return flights;
	}

	public FlightPriceSearch flightConverter(int id, int userId) {
		FavoriteModel  favorite = favoriteRepository.findByIdAndUserId(id, userId);

		if (favorite == null) {
			throw new BusinnesException("Não encontramos favoritos para o usuário: "+ id);
		}
		
		Gson json = new Gson();
		FlightPriceSearch flight = json.fromJson(favorite.getFlight(), FlightPriceSearch.class);; 

		return flight;
	}
}
