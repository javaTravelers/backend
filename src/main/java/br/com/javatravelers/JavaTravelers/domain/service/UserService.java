package br.com.javatravelers.JavaTravelers.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;
import br.com.javatravelers.JavaTravelers.infra.security.service.AuthenticationFacade;

@Service
public class UserService {

	@Autowired
	private UserAuthRepository userRepository;
	
	@Autowired
	private AuthenticationFacade auth;
	
	public UserAuthModel findAuthUser() {
		UserAuthModel user = userRepository.findByEmail(auth.getAuth().getName());	
		if (user == null) {
			throw new BusinnesException("Usuário não localizado!");
		}		
		return user;
	}
}
