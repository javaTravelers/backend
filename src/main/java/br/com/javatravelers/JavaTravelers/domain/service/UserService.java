package br.com.javatravelers.JavaTravelers.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.UserModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserModel saveUser(UserModel userModel) throws BusinnesException{
		
		UserModel userModelDB = userRepository.findByEmail(userModel.getEmail());
		
		if ((userModelDB != null) && (!userModelDB.equals(userModel))) {
			throw new BusinnesException("Já existe um usuário cadastrado com esse e-mail!");
		}
		return userRepository.save(userModel);
	}
}
