package br.com.javatravelers.JavaTravelers.service.pagseguro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.BaseException;

import br.com.javatravelers.JavaTravelers.domain.enums.PaymentStatus;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.PaymentModel;
import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.repository.PaymentRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;
	
	public PaymentResult generatedUrlToCheckout(Payment_items items) {
		
		Checkout checkout = new Checkout();
		List<Payment_items> payment_items = new ArrayList<Payment_items>();
		String bodyXML = "";
		Response response = null;
		String responseCode = "";
		
		Receiver receiver = new Receiver();
		receiver.setEmail(InformationsAndUtils.EMAIL);
		
		Payment_items item = new Payment_items();
		item.setId(items.getId());
		item.setDescription(items.getDescription());
		item.setAmount(items.getAmount());
		item.setQuantity(items.getQuantity());
		payment_items.add(item);
		
		checkout.setPayment_items(payment_items);
		checkout.setCurrency(InformationsAndUtils.CURRENCY);
		checkout.setReceiver(receiver);
		
		
		XStream xstream = new XStream();
		xstream.alias("checkout", Checkout.class);
		xstream.alias("item", Payment_items.class);
		xstream.aliasField("items", Checkout.class, "payment_items");
		
		try {
			bodyXML = xstream.toXML(checkout);
		
		} catch (BaseException e) {
			throw new BusinnesException("Não foi possível realizar a conversão do objeto em xml."); 
		}
		
		OkHttpClient httpClient = new OkHttpClient();
		
		RequestBody body = RequestBody.create(InformationsAndUtils.MEDIA_TYPE, bodyXML);
		Request request = new Request.Builder()
			.url(InformationsAndUtils.LINK_POST)
			.post(body)
			.addHeader("content-type", InformationsAndUtils.CONTENT_XML)
			.build();

		try {
			response = httpClient.newCall(request).execute();
			responseCode = response.body().string();
		
		} catch (IOException e) {
			throw new BusinnesException("Algo errado ocorreu durante a comunicação com o servidor de pagamento. (Erro: " + response.code() + ")");
		}
		
		if(response.code() == 200) {
			System.out.println(response.code());
			
			String code = responseCode.substring(responseCode.indexOf("<code>"), responseCode.indexOf("</code>")).replaceFirst("<code>", "");
			//String date = responseCode.substring(responseCode.indexOf("<date>"), responseCode.indexOf("</date>")).replaceFirst("<date>", "");
			
			PaymentResult result = new PaymentResult();
			result.setCode(code);
			String url = InformationsAndUtils.LINK_CHECKOUT + code;
			result.setUrl(url);
			
			
			return result;
			
		} else {
			throw new BusinnesException("A comunicação com o servidor obteve sucesso porém ocorreu o erro: " + response.code());
		}
	}
	
	public PaymentModel create(TicketModel ticket) {
		PaymentModel payment = new PaymentModel();
		Date date = new Date();
		payment.setCodTransacao(ticket.getPaymentId());
		payment.setDataTransacao(date);
		payment.setDataStatus(date);
		payment.setUrl(ticket.getPaymentUrl());
		payment.setUserId(ticket.getUserId());
		payment.setValorTransacao(ticket.getPrice());
		payment.setStatus(PaymentStatus.PROCESSANDO);
		paymentRepository.save(payment);
		return payment;
		
	}
}
