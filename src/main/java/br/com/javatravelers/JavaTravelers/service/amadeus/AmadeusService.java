package br.com.javatravelers.JavaTravelers.service.amadeus;

import java.awt.List;
import java.util.ArrayList;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.amadeus.shopping.FlightOffers;
import com.amadeus.shopping.FlightOffersSearch;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPayment;
import com.amadeus.resources.FlightPrice;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class AmadeusService {

	private Amadeus amadeus;

	public AmadeusService () {
		this.amadeus = Amadeus
				.builder("RZ3pbc22VwLAXtXqIU80DmT2hv1M2J8y", "zVyCOrYgU6ThENBK")
				.build();
	}

	public void flightOffers(String originCode, String destinationCode, String departureDate, String returnDate, int adults) {

		FlightOfferSearch[] flightOffers = null;

		try {
			flightOffers = amadeus.shopping.flightOffersSearch.get(
					Params.with("originLocationCode", originCode)
					.and("destinationLocationCode", destinationCode)
					.and("departureDate", departureDate)
					.and("returnDate", returnDate)
					.and("adults", adults)
					.and("currencyCode", "BRL"));

			System.out.println("Ofertas de Vôos");
			System.out.println(flightOffers[0]);


			JsonObject body = flightOffers[0].getResponse().getResult();
			FlightOfferSearch[] flightOffersPrediction = amadeus.shopping.flightOffers.prediction.post(body);

			if (flightOffersPrediction[0].getResponse().getStatusCode() != 200) {
				System.out.println("Wrong status code: " + flightOffersPrediction[0].getResponse().getStatusCode());
				System.exit(-1);
			}
			System.out.println("Preços das Ofertas");
			System.out.println(flightOffersPrediction[0]);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void searchCities(String search) {
		// Airport & City Search (autocomplete)
		// Find all the cities and airports starting by the keyword 'LON'
		Location[] locations;
		try {
			locations = amadeus.referenceData.locations.get(Params
					.with("keyword", search)
					.and("subType", Locations.ANY));
			if(locations[0].getResponse().getStatusCode() != 200) {
				System.out.println("Wrong status code: " + locations[0].getResponse().getStatusCode());
				System.exit(-1);
			}
			for (int i = 0; i < locations.length && i < 5; i++)
				System.out.println(locations[0]);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchPrice() {
		try {
			FlightOfferSearch[] flightOffersSearches = amadeus.shopping.flightOffersSearch.get(
					Params.with("originLocationCode", "SYD")
					.and("destinationLocationCode", "BKK")
					.and("departureDate", "2020-11-10")
					.and("returnDate", "2020-11-20")
					.and("adults", 1)
					.and("max", 2));

			// We price the 2nd flight of the list to confirm the price and the availability
			FlightPrice flightPricing;
			flightPricing = amadeus.shopping.flightOffersSearch.pricing.post(
					flightOffersSearches[0],
					Params.with("include", "detailed-fare-rules")
					.and("forceClass", "false")
					);
			FlightOfferSearch[] flight = flightPricing.getFlightOffers();
			System.out.println(flight[0]);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AmadeusService amadeus = new AmadeusService();

		//amadeus.flightOffers("NYC", "LON", "2020-11-23", "2020-11-29", 1);


		//System.out.println("\nCidades e Aeroportos");

		//amadeus.searchCities("Lond");
		amadeus.searchPrice();
	}


}
