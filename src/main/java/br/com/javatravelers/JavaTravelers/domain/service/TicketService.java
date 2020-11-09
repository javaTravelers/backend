package br.com.javatravelers.JavaTravelers.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;

@Service
public class TicketService {


	@Autowired
	private UserService userService;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	public TicketModel ticketConverter(FlightOrderGet flight) {
		UserAuthModel user = userService.findAuthUser();

		Gson json = new Gson();
		String strFlight = json.toJson(flight);

		TicketModel ticketModel = new TicketModel();
	
		ticketModel.setFlight(strFlight);
		ticketModel.setUserId(user.getId());

		return ticketModel;
	}

	public FlightOrderGet ticketConverter(String reserva) {
		UserAuthModel user = userService.findAuthUser();
		TicketModel  ticket = ticketRepository.findByReservationNumberAndUserId(reserva, user.getId());

		if (ticket == null) {
			throw new BusinnesException("Não encontramos a reserva: "+ reserva);
		}
		
		Gson json = new Gson();
		FlightOrderGet flight = json.fromJson(ticket.getFlight(), FlightOrderGet.class);; 

		return flight;
	}
	
	public TicketModel getTicket(String reserva) {
		UserAuthModel user = userService.findAuthUser();
		TicketModel  ticket = ticketRepository.findByReservationNumberAndUserId(reserva, user.getId());

		if (ticket == null) {
			throw new BusinnesException("Não encontramos a reserva: "+ reserva);
		}
		
		return ticket;
	}

}
