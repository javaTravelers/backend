package br.com.javatravelers.JavaTravelers.domain.repository;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	public UserModel findByEmail(String email);
	public UserModel findById(int id);
	
}
