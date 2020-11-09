package br.com.javatravelers.JavaTravelers.service.amadeus;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;
import br.com.javatravelers.JavaTravelers.domain.service.UserService;
import br.com.javatravelers.JavaTravelers.service.amadeus.exception.TicketException;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;


@Service
public class AmadeusService {

	private Amadeus amadeus;
	private AmadeusServiceUtil ams;
	
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	public AmadeusService () {
		this.amadeus = Amadeus
				.builder("RZ3pbc22VwLAXtXqIU80DmT2hv1M2J8y", "zVyCOrYgU6ThENBK")
				.build();
		this.ams = new AmadeusServiceUtil(amadeus);
	}
	
	public String flightOffers(Map<String, String> request) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FlightOfferSearch[] flightOffers = null;
		String jsons = null;
		try {
			flightOffers = amadeus.shopping.flightOffersSearch.get(
					Params.with("originLocationCode", request.get("originCode"))
					.and("destinationLocationCode", request.get("destinationCode"))
					.and("departureDate", request.get("departureDate"))
					.and("returnDate", request.get("returnDate"))
					.and("adults", request.get("adults"))
					////the number of child travelers (older than age 2 and younger than age 12 on date of departure) who will each have their own separate seat.
					.and("infant", request.get("children"))
					.and("currencyCode", "BRL")
					.and("max", "10"));

			if (flightOffers.length <= 0) {
				throw new BusinnesException("[400] - Não foram encontrados vôos com esses parâmetros.");
			}

			jsons = gson.toJson(flightOffers);
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
		return jsons;
	}

	public FlightOfferResult flightOffers(FlightOfferGet request) {
		FlightOfferResult flightOffers = null;
		try {
			flightOffers = ams.getOffer(
					Params.with("originLocationCode", request.getOriginCode())
					.and("destinationLocationCode", request.getDestinationCode())
					.and("departureDate", request.getDepartureDate())
					.and("returnDate", request.getReturnDate())
					.and("adults", request.getAdults())
					////the number of child travelers (older than age 2 and younger than age 12 on date of departure) who will each have their own separate seat.
					.and("children", request.getChildren())
					.and("infants", request.getInfants())
					.and("currencyCode", request.getCurrencyCode())
					.and("max", request.getMax()));
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
		return flightOffers;
	}

	public FlightOfferResult flightOffers(OffersSearch offersSearch) {
		Gson gson = new Gson();
		String json = gson.toJson(offersSearch);
		FlightOfferResult flighOfferss = null;
		try {
			flighOfferss = ams.postOffer(json);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flighOfferss;
	}

	public String searchLocations(SearchLocation search) {
		// Airport & City Search (autocomplete)
		// Find all the cities and airports starting by the keyword 'LON'
		Gson json = new Gson();
		String response = null;
		Location[] locations = null;
		try {
			locations = amadeus.referenceData.locations.get(Params
					.with("keyword", search.getLocation())
					.and("subType", Locations.ANY));
			response = json.toJson(locations);
			if(locations.length <= 0) {
				throw new BusinnesException("[400] Local não encontrado.");
			}
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
		return response;
	}

	public FlightPriceResult searchPrice(FlightPriceSearch flight) {
		Gson json = new Gson();
		String search = json.toJson(flight);

		FlightPriceResult flightPricing;
		try {
			// We price the 2nd flight of the list to confirm the price and the availability
			flightPricing = ams.getPrice(search);
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
		return flightPricing;
	}

	public FlightOrderResult createOrder(FlightOrderGet flight) {
		ObjectMapper mapper = new ObjectMapper();
		String create = "";
		//Object to JSON in String
		try {
			create = mapper.writeValueAsString(flight);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FlightOrderResult response = null;
		try {
			response = ams.createOrder(create);
			System.out.println(response);

		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}

		return response;
	}
	
	public FlightOrderResult viewOrder(String id) {
		try {
			return ams.viewOrder(id);
		} catch (ResponseException e) {
			throw new TicketException("A reserva solicitada não existe.", 400);
		}
	}
	
	public boolean deleteOrder(String id) {
		try {
			return ams.deleteOrder(id);
		} catch (ResponseException e) {
			throw new TicketException("A reserva foi excluída com sucesso.", 200);
		}
	}
}
