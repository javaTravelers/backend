package br.com.javatravelers.JavaTravelers.service.pagseguro;

import okhttp3.MediaType;

public class InformationsAndUtils {

	public static final String EMAIL = "adriano.estudio8@gmail.com";
	public static final String TOKEN = "F29DC8A632424B32B22147C240982E48";
	public static final String NOTIFICATION_URL = "https://java-travelers-teste.azurewebsites.net/payment/order/response";
	public static final String LINK_CHECKOUT = "https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=";
	public static final String LINK_POST = "https://ws.sandbox.pagseguro.uol.com.br/v2/checkout?email=" + EMAIL + "&token=" + TOKEN;
	public static final String CURRENCY = "BRL";
	public static final String CONTENT_XML = "application/xml"; 
	public static final MediaType MEDIA_TYPE = MediaType.parse(CONTENT_XML);
	
}
