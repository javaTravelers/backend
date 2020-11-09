package br.com.javatravelers.JavaTravelers.domain.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.FavoriteModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.FavoriteRepository;

@Service
public class FavoriteService {

	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private UserService userService;

	public FavoriteModel favoriteConverter(FlightPriceSearch flight) {
		UserAuthModel user = userService.findAuthUser();

		Gson json = new Gson();
		String strFlight = json.toJson(flight);

		FavoriteModel favoriteModel = new FavoriteModel();

		favoriteModel.setFlight(strFlight);
		favoriteModel.setUserId(user.getId());

		return favoriteModel;
	}

	public List<FlightPriceSearch> flightConverter() {
		UserAuthModel user = userService.findAuthUser();
		List<FavoriteModel>  favorites = favoriteRepository.findAllByUserId(user.getId());

		if (favorites.size() <= 0) {
			throw new BusinnesException("Não encontramos favoritos para o usuário: " + user.getId());
		}

		List<FlightPriceSearch> flights = new ArrayList<FlightPriceSearch>(); 
		Gson json = new Gson();
		for (int i = 0; i < favorites.size(); i++) {
			flights.add(json.fromJson(favorites.get(i).getFlight(), FlightPriceSearch.class));
		}

		return flights;
	}

	public FlightPriceSearch flightConverter(Integer id) {
		UserAuthModel user = userService.findAuthUser();
		FavoriteModel  favorite = favoriteRepository.findByIdAndUserId(id, user.getId());

		if (favorite == null) {
			throw new BusinnesException("Não encontramos favoritos para o usuário: "+ user.getId());
		}

		Gson json = new Gson();
		FlightPriceSearch flight = json.fromJson(favorite.getFlight(), FlightPriceSearch.class);; 

		return flight;
	}
	
	public boolean favoriteDelete(Integer id) {
		UserAuthModel user = userService.findAuthUser();

		if (!favoriteRepository.existsByIdAndUserId(id, user.getId())) {
			return false;

		} else {
			favoriteRepository.deleteById(id);
			return true;
		}
	}
}
