package br.com.javatravelers.JavaTravelers.service.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.shopping.flightOffers.Pricing;
import com.google.gson.Gson;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.flight.FlightOfferResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.price.FlightPriceResult;
import br.com.javatravelers.JavaTravelers.service.amadeus.exception.TicketException;



public class AmadeusServiceUtil extends Pricing {

	private Amadeus client;

	public AmadeusServiceUtil(Amadeus client) {
		super(client);
		this.client = client;
	}

	public FlightOfferResult postOffer(String body) throws ResponseException {
		Response response = client.post("/v2/shopping/flight-offers", body);
		if (response.getStatusCode() != 200) {
			throw new TicketException("[400] - A passagem solicitada não foi encontrada", 400);
		}

		Gson json = new Gson();
		System.out.println(response.getResult());
		FlightOfferResult result = json.fromJson(response.getResult(), FlightOfferResult.class);
		
		return  result;
	}

	public FlightOfferResult getOffer(Params params) throws ResponseException {
		Response response = client.get("/v2/shopping/flight-offers", params);
		if (response.getStatusCode() != 200) {
			throw new TicketException("[400] - Não foram encontrados vôos com esses parâmetros.", 400);
		}
		Gson json = new Gson();
		FlightOfferResult result = json.fromJson(response.getResult(), FlightOfferResult.class);

		return result;
	}

	public FlightPriceResult getPrice(String body) throws ResponseException {
		Response response = client.post("/v1/shopping/flight-offers/pricing", body);
		if (response.getStatusCode() != 200) {
			throw new TicketException("[400] - A passagem solicitada não foi encontrada", 400);
		}
		Gson json = new Gson();
		FlightPriceResult result = json.fromJson(response.getResult(), FlightPriceResult.class);

		return result;
	}

	public FlightOrderResult createOrder(String body) throws ResponseException {
		Response response = client.post("/v1/booking/flight-orders", body);
		Gson json = new Gson();
		FlightOrderResult result = json.fromJson(response.getResult(), FlightOrderResult.class);

		return result;
	}

	public FlightOrderResult viewOrder(String flightOfferId) throws ResponseException {
		String path = String.format("/v1/booking/flight-orders/%s", flightOfferId);
	    Response response = client.get(path, null);
		Gson json = new Gson();
		FlightOrderResult result = json.fromJson(response.getResult(), FlightOrderResult.class);

		return result;
	}
	
	public boolean deleteOrder(String flightOfferId) throws ResponseException {
		String path = String.format("/v1/booking/flight-orders/%s", flightOfferId);
		client.delete(path, null);
		return true;
	}
	
	
}
