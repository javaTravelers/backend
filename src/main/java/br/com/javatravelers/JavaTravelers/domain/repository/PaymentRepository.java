package br.com.javatravelers.JavaTravelers.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.PaymentModel;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Integer> {
	public List<PaymentModel> findAllByUserId(int id);
}
