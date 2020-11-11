package br.com.javatravelers.JavaTravelers.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.infra.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import okhttp3.Credentials;
import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;
import br.com.javatravelers.JavaTravelers.domain.model.dto.Credencial;
import br.com.javatravelers.JavaTravelers.domain.model.dto.Login;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;
import br.com.javatravelers.JavaTravelers.domain.service.CadastroService;

@RestController
@RequestMapping("/")
@Api(value = "Autenticação do Usuário")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@Autowired
	private CadastroService service;

	@Autowired
	private UserAuthRepository userRepository;

	@ApiOperation(value = "Salvar novo usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo usuário criado com resultado."),
			@ApiResponse(code = 400, message = "Uns dos campos do cadastro não foi preenchido, ou preenchido incorretamente."),
			@ApiResponse(code = 422, message = "Cpf inválido ou e-mail já existente."),
	})
	@PostMapping(consumes = "application/json", produces = "application/json", value="/signup")
	public void signin(@RequestBody UserAuthModel cliente) {
		service.save(cliente);
	}

	@ApiOperation(value = "Fazer login na API")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida. Usuário logado com sucesso.", response = Credentials.class),
			@ApiResponse(code = 400, message = "Uns dos campos do cadastro não foi preenchido, ou preenchido incorretamente."),
			@ApiResponse(code = 422, message = "Cpf inválido ou e-mail já existente."),
	})
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {

		UserAuthModel user = userRepository.findByEmail(login.getUsuario());
		Credencial credencial= new Credencial();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							login.getUsuario(),
							login.getSenha()
							)
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(authentication);
			credencial.setLogin(login.getUsuario());
			credencial.setToken(token);
			credencial.setUserId(user.getId());;
		} catch (BadCredentialsException e){

			throw new BusinnesException("Usuário e/ou senha incorretos!");
		} 

		return ResponseEntity.ok(credencial); 
	}
}
