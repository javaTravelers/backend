package br.com.javatravelers.JavaTravelers.services.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import com.google.gson.JsonObject;

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
	
	public static void main(String[] args) {
		AmadeusService amadeus = new AmadeusService();
		
		amadeus.flightOffers("NYC", "LON", "2020-11-23", "2020-11-29", 1);
	}


}
