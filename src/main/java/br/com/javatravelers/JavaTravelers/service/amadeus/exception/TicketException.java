package br.com.javatravelers.JavaTravelers.service.amadeus.exception;

import lombok.Getter;

@Getter
public class TicketException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer statusCode;
	public TicketException (String message, Integer statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

}
