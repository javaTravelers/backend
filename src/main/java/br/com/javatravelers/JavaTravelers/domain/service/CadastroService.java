package br.com.javatravelers.JavaTravelers.domain.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.Role;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;
import br.com.javatravelers.JavaTravelers.uteis.ValidaCPF;

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
			usuario = validateUser(usuario);
			usuario = userAuthRepository.save(usuario);
			return usuario.getId();
		}
		return null;
	}

	public Integer saveOperador(UserAuthModel operador) {
		return saveUsuario(operador);
	}


	public Integer save(UserAuthModel cliente) { 
		if(cliente.getId()==null) {

			cliente.setRoles(new HashSet<>()); 
			cliente.addRole(new Role("USER")); 
		}
		return saveUsuario(cliente);

	}

	public UserAuthModel validateUser(UserAuthModel userModel) throws BusinnesException{

		UserAuthModel userModelDB = userAuthRepository.findByEmail(userModel.getEmail());

		if ((userModelDB != null) && (!userModelDB.equals(userModel))) {
			throw new BusinnesException("Já existe um usuário cadastrado com esse e-mail.");
		}

		if (!ValidaCPF.cpfValid(userModel.getCpf())) {
			throw new BusinnesException("O cpf informado é inválido.");
		}

		return userModel;
	}



}
