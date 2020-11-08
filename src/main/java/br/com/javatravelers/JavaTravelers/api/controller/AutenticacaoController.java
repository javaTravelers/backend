package br.com.javatravelers.JavaTravelers.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.infra.security.JwtTokenProvider;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;
import br.com.javatravelers.JavaTravelers.domain.model.dto.Credencial;
import br.com.javatravelers.JavaTravelers.domain.model.dto.Login;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;
import br.com.javatravelers.JavaTravelers.domain.service.CadastroService;

@RestController
@RequestMapping("/")
public class AutenticacaoController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenUtil;
	
	@Autowired
	private CadastroService service;
	
	@Autowired
	private UserAuthRepository userRepository;
	
	@PostMapping("/signup")
	public void signin(@RequestBody UserModel cliente) {
		service.save(cliente);
	}
	    
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {
		
		UserAuthModel user = userRepository.findByLogin(login.getUsuario());
		
		final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getUsuario(),
                		login.getSenha()
                )
        );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
        Credencial credencial= new Credencial();
        credencial.setLogin(login.getUsuario());
        credencial.setToken(token);
        credencial.setUserId(user.getId());;
      
		return ResponseEntity.ok(credencial);
		
        
	}
}
