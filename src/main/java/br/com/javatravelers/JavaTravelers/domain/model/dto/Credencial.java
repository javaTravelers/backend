package br.com.javatravelers.JavaTravelers.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credencial {
	
	private String login;
	private String token;
	private Integer userId;
}