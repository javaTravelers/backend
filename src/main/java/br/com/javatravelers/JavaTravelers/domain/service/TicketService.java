package br.com.javatravelers.JavaTravelers.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.javatravelers.JavaTravelers.domain.enums.PaymentStatus;
import br.com.javatravelers.JavaTravelers.domain.enums.TicketStatus;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderGet;
import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.FlightOrderResult;
import br.com.javatravelers.JavaTravelers.domain.repository.TicketRepository;
import br.com.javatravelers.JavaTravelers.service.amadeus.exception.TicketException;

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
		ticketModel.setFlightGet(strFlight);
		ticketModel.setUserId(user.getId());

		return ticketModel;
	}

	public FlightOrderGet ticketConverter(String reserva) {
		UserAuthModel user = userService.findAuthUser();
		TicketModel  ticket = ticketRepository.findByReferenceAndUserId(reserva, user.getId());
		if (ticket == null) {
			throw new TicketException("[400] - Não encontramos a reserva: "+ reserva, 400);
		}
		Gson json = new Gson();
		FlightOrderGet flight = json.fromJson(ticket.getFlightGet(), FlightOrderGet.class);; 

		return flight;
	}
	
	public TicketModel getTicket(String reserva) {
		UserAuthModel user = userService.findAuthUser();
		TicketModel  ticket = ticketRepository.findByReferenceAndUserId(reserva, user.getId());
		if (ticket == null) {
			throw new TicketException("[400] - Não encontramos a reserva: "+ reserva, 400);
		}
		
		return ticket;
	}
	
	public List<TicketModel> listTickets() {
		UserAuthModel user = userService.findAuthUser();
		List<TicketModel>  tickets = ticketRepository.findAllByUserId(user.getId());
		if (tickets.size() <= 0) {
			throw new TicketException("[400] - Não existem tickets para esse usuário.", 400);
		}	
		
		return tickets;
	}
	
	public TicketModel deleteTicket(String reservationId) {
		UserAuthModel user = userService.findAuthUser();
		TicketModel  ticket = ticketRepository.findByReservationIdAndUserId(reservationId, user.getId());
		if (ticket == null ) {
			throw new TicketException("[400] - A reserva informada não foi localizada.", 400);
		}
		ticket.setStatus(TicketStatus.CANCELADO);
		ticket = ticketRepository.save(ticket);
		return ticket;
	}
}
