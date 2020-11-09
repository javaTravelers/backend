package br.com.javatravelers.JavaTravelers.infra.security.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationInterface {
	Authentication getAuth();
}

