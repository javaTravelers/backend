package br.com.javatravelers.JavaTravelers.infra.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements AuthenticationInterface {
	@Override
    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
