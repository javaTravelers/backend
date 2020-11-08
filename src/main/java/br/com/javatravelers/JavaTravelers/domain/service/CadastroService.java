package br.com.javatravelers.JavaTravelers.domain.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.javatravelers.JavaTravelers.domain.model.Operador;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.Role;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;

@Service
public class CadastroService {
		
	@Autowired
	private UserAuthRepository userAuthRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Integer saveUsuario(UserAuthModel usuario) {
	
		String senhaCriptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		if(!userAuthRepository.existsByLogin(usuario.getLogin())) {
			usuario = userAuthRepository.save(usuario);
			return usuario.getId();
		}
		return null;
	}
	public Integer save(Operador operador) {
		return saveUsuario(operador);
	}
	
	public Integer save(UserModel cliente) {
		if(cliente.getId()==null) {
			cliente.setRoles(new HashSet<>());
			cliente.addRole(new Role("USER"));
		}
		 return saveUsuario(cliente);
		
	}
	
	
}
