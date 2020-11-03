package br.com.javatravelers.JavaTravelers.service.amadeus;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.service.amadeus.resource.SearchLocation;

@Service
public class AmadeusService {

	private Amadeus amadeus;

	public AmadeusService () {
		this.amadeus = Amadeus
				.builder("RZ3pbc22VwLAXtXqIU80DmT2hv1M2J8y", "zVyCOrYgU6ThENBK")
				.build();
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
					.and("currencyCode", "BRL")
					.and("max", "1"));

			System.out.println("Ofertas de Vôos");
			System.out.println(flightOffers[0]);

			jsons = gson.toJson(flightOffers);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsons;
	}

	public String flightOffers(String flight) {
		Gson gson = new Gson();
		// String example: {\"originDestinations\":[{\"id\":\"1\",\"originLocationCode\":\"NYC\",\"destinationLocationCode\":\"LON\",\"departureDateTimeRange\":{\"date\":\"2020-12-01\",\"time\":\"10:00:00\"}},{\"id\":\"2\",\"originLocationCode\":\"LON\",\"destinationLocationCode\":\"RIO\",\"departureDateTimeRange\":{\"date\":\"2020-12-05\",\"time\":\"17:00:00\"}}],\"travelers\":[{\"id\":\"1\",\"travelerType\":\"ADULT\",\"fareOptions\":[\"STANDARD\"]},{\"id\":\"2\",\"travelerType\":\"CHILD\",\"fareOptions\":[\"STANDARD\"]}],\"sources\":[\"GDS\"],\"searchCriteria\":{\"oneWay\":\"true\",\"maxFlightOffers\":10,\"flightFilters\":{\"cabinRestrictions\":[{\"cabin\":\"BUSINESS\",\"coverage\":\"MOST_SEGMENTS\",\"originDestinationIds\":[\"1\"]}],\"carrierRestrictions\":{\"excludedCarrierCodes\":[\"AA\",\"TP\",\"AZ\"]}}}}";
		String jsons = null;
		FlightOfferSearch[] flightOffers = null;
		try {
			flightOffers = amadeus.shopping.flightOffersSearch.post(flight);
			jsons = gson.toJson(flightOffers, FlightOfferSearch[].class);
		} catch (ResponseException e) {
			e.printStackTrace();
		}
		return jsons;
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
			if(locations[0].getResponse().getStatusCode() != 200) {
				System.out.println("Wrong status code: " + locations[0].getResponse().getStatusCode());
				System.exit(-1);
			}
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage());
		}
		return response;
	}

	public String searchPrice(String flight) {
		Gson json = new Gson();
		String response = null;
		try {
			// We price the 2nd flight of the list to confirm the price and the availability
			FlightPrice flightPricing;
			flightPricing = amadeus.shopping.flightOffersSearch.pricing.post(
					flight);
			response = json.toJson(flightPricing);
			System.out.println(flightPricing);
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage());
		}
		return response;
	}
}
