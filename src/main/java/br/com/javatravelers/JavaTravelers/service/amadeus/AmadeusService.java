package br.com.javatravelers.JavaTravelers.service.amadeus;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.OffersSearch;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceSearch;
import br.com.javatravelers.JavaTravelers.domain.service.TicketService;
import br.com.javatravelers.JavaTravelers.service.amadeus.exception.TicketException;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;


@Service
public class AmadeusService {

	private Amadeus amadeus;
	private AmadeusServiceUtil ams;
	
	@Autowired
	private TicketService ticketService;
	
	public AmadeusService () {
		this.amadeus = Amadeus
				.builder("RZ3pbc22VwLAXtXqIU80DmT2hv1M2J8y", "zVyCOrYgU6ThENBK")
				.build();
		this.ams = new AmadeusServiceUtil(amadeus);
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
			throw new TicketException(e.getMessage().replaceAll("\n", " - "), Integer.parseInt(e.getCode()));
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
				throw new TicketException("[400] - Local não encontrado.", 400);
			}
		} catch (ResponseException e) {
			throw new TicketException(e.getMessage().replaceAll("\n", " - "), Integer.parseInt(e.getCode()));
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
			throw new TicketException(e.getMessage().replaceAll("\n", " - "), Integer.parseInt(e.getCode()));
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
			throw new TicketException(e1.getMessage().replaceAll("\n", " - "), 500);
		}
		FlightOrderResult response = null;
		try {
			response = ams.createOrder(create);
			System.out.println(response);

		} catch (ResponseException e) {
			throw new TicketException(e.getMessage().replaceAll("\n", " - "), Integer.parseInt(e.getCode()));
		}

		return response;
	}
	
	public FlightOrderResult viewOrder(String id) {
		try {
			return ams.viewOrder(id);
		} catch (ResponseException e) {
			throw new TicketException("[400] - A reserva solicitada não existe.", 400);
		}
	}
	
	public boolean deleteOrder(String reservationId) {
		ticketService.deleteTicket(reservationId);
		try {
			return ams.deleteOrder(reservationId);
		} catch (ResponseException e) {
			throw new TicketException("A reserva foi excluída com sucesso.", 200);
		}
	}
}
