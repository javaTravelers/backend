package br.com.javatravelers.JavaTravelers.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.amadeus.order.Traveler;

@Repository
public interface TicketRepository extends JpaRepository<Traveler, Integer> {

}
