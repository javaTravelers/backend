package br.com.javatravelers.JavaTravelers.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.TicketModel;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Integer> {
	public TicketModel findByPaymentId(String paymentId );
	public TicketModel findByReference(String reserva);
	public TicketModel findByReferenceAndUserId(String reserva, int userId);
	public TicketModel findByReservationIdAndUserId(String reservationId, int userId);
	public List<TicketModel> findAllByUserId(Integer userId);
}
