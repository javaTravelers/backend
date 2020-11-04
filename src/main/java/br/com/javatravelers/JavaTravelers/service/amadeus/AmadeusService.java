package br.com.javatravelers.JavaTravelers.service.amadeus;


import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.amadeus.resources.Traveler;
import com.amadeus.resources.Traveler.Document;
import com.amadeus.resources.Traveler.Phone;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
			
			if (flightOffers.length <= 0) {
				throw new BusinnesException("[400] - Não foram encontrados vôos com esses parâmetros.");
			}

			jsons = gson.toJson(flightOffers);
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
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
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
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
			if(locations.length <= 0) {
				throw new BusinnesException("[400] Local não encontrado.");
			}
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
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
			if (flightPricing.getResponse().getStatusCode() != 200) {
				throw new BusinnesException("Erro ao localizar a passagem!");
			}
			response = json.toJson(flightPricing.getResponse().getResult());
		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
		return response;
	}
	
	public String createOrder(String flight) {
		Gson json = new Gson();
		String response = null;
		
		Traveler traveler = new Traveler();

	    traveler.setId("1");
	    traveler.setDateOfBirth("2000-04-14");
	    traveler.setName(traveler.new Name("JORGE", "GONZALES"));

	    Traveler.Phone[] phone = new Phone[1];
	    phone[0] = traveler.new Phone();
	    phone[0].setCountryCallingCode("33");
	    phone[0].setNumber("675426222");
	    phone[0].setDeviceType("MOBILE");

	    Traveler.Contact contact = traveler.new Contact();
	    contact.setPhones(phone);
	    traveler.setContact(contact);

	    Traveler.Document[] document = new Document[1];
	    document[0] = traveler.new Document();
	    document[0].setDocumentType("PASSPORT");
	    document[0].setNumber("480080076");
	    document[0].setExpiryDate("2022-10-11");
	    document[0].setIssuanceCountry("ES");
	    document[0].setNationality("ES");
	    document[0].setHolder(true);
	    traveler.setDocuments(document);

	    Traveler[] travelerArray = new Traveler[1];
	    travelerArray[0] = traveler;


	    // We book the flight previously priced
	    FlightOrder order = null;
		try {
			order = amadeus.booking.flightOrders.post(flight);
			System.out.println(order);
			if (order == null)
				throw new BusinnesException("A passagem solicitada não existe.");
			
			if (order.getResponse().getStatusCode() != 200 && order.getResponse().getStatusCode() != 201)
				throw new BusinnesException("" + order.getResponse().getStatusCode());

		} catch (ResponseException e) {
			throw new BusinnesException(e.getMessage().replaceAll("\n", " - "));
		}
	    response = json.toJson(order.getResponse().getResult());
		
		return response;
	}
}
