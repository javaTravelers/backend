package br.com.javatravelers.JavaTravelers.domain.repository;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuthModel, Integer>{
	
	UserAuthModel findByLogin(String login);
	boolean existsByLogin(String login);
	
	UserAuthModel findByEmail(String email);
	
}
