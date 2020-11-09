package br.com.javatravelers.JavaTravelers.infra.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;
import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserAuthRepository;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;


@Service(value = "jwtUserService")
public class JwtUserService implements UserDetailsService  {
	
	@Autowired
	private UserAuthRepository repository;
	
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserAuthModel usuario = repository.findByEmail(login);
		if(usuario == null){
			throw new UsernameNotFoundException("Usuário não existe");
		}
		Set<SimpleGrantedAuthority> roles = getAuthority(usuario);
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), roles);
	}
	private Set<SimpleGrantedAuthority> getAuthority(UserAuthModel user){
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRole().toUpperCase()));
		});
		return authorities;
	}
}
