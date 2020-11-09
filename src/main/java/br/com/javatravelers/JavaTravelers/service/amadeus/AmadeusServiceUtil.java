package br.com.javatravelers.JavaTravelers.service.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Response;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOrder;
import com.amadeus.resources.FlightPrice;
import com.amadeus.resources.Resource;
import com.amadeus.shopping.flightOffers.Pricing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
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
			throw new TicketException("A passagem solicitada não foi encontrada", 400);
		}

		/*
		 * try { //JSON from Object to String String json =
		 * mapper.writeValueAsString(response.getResult()); //JSON from String to Object
		 * System.out.println(json); System.out.println(json); result =
		 * mapper.readValue(json, FlightOfferResult.class); } catch
		 * (JsonMappingException e) { throw new
		 * TicketException("Ocorreu um erro na requisição: 1 "+ e.getLocalizedMessage(),
		 * 500); } catch (JsonProcessingException e) { throw new
		 * TicketException("Ocorreu um erro na requisição: "+ e.getMessage(), 500); }
		 */
		Gson json = new Gson();
		System.out.println(response.getResult());
		FlightOfferResult result = json.fromJson(response.getResult(), FlightOfferResult.class);

		System.out.println(result);
		return  result;
	}

	public FlightOfferResult getOffer(Params params) throws ResponseException {
		Response response = client.get("/v2/shopping/flight-offers", params);
		if (response.getStatusCode() != 200) {
			throw new TicketException("[400] - Não foram encontrados vôos com esses parâmetros.", 400);
		}

		Gson json = new Gson();
		System.out.println(response.getResult());
		FlightOfferResult result = json.fromJson(response.getResult(), FlightOfferResult.class);

		return result;
	}

	public FlightPriceResult getPrice(String body) throws ResponseException {
		Response response = client.post("/v1/shopping/flight-offers/pricing", body);
		if (response.getStatusCode() != 200) {
			throw new TicketException("A passagem solicitada não foi encontrada", 400);
		}
		Gson json = new Gson();
		System.out.println(response.getResult());
		FlightPriceResult result = json.fromJson(response.getResult(), FlightPriceResult.class);

		return result;
	}

	public FlightOrderResult createOrder(String body) throws ResponseException {
		Response response = client.post("/v1/booking/flight-orders", body);
		Gson json = new Gson();
		System.out.println(response.getResult());
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
		System.out.println("Resposta " + client.delete(path, null));
		//Response response = client.delete(path, null);
	    //System.out.println(response.getStatusCode());
		Gson json = new Gson();
		//FlightOrderResult result = json.fromJson(response.getResult(), FlightOrderResult.class);

		return true;
	}
	
	
}
