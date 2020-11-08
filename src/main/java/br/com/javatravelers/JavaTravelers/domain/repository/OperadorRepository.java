package br.com.javatravelers.JavaTravelers.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.javatravelers.JavaTravelers.domain.model.Operador;


public interface OperadorRepository extends JpaRepository<Operador, Integer> {
	Operador findByLogin(String login);
	boolean existsByLogin(String login);
}
