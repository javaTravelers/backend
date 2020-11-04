package br.com.javatravelers.JavaTravelers.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.javatravelers.JavaTravelers.domain.exception.BusinnesException;
import br.com.javatravelers.JavaTravelers.service.amadeus.exception.TicketException;
import lombok.var;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(BusinnesException.class)
	public ResponseEntity<Object> handleDuplicatedEmail(BusinnesException ex, WebRequest request) {
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo(ex.getMessage());
		problem.setDataHora(LocalDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(TicketException.class)
	public ResponseEntity<Object> handleTicketErrors(TicketException ex, WebRequest request) {
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		var statusCode = ex.getStatusCode();
		System.out.println(statusCode);
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo(ex.getMessage());
		problem.setDataHora(LocalDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var campos = new ArrayList<Problem.Campo>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problem.Campo(nome, mensagem));
		}
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo("Um ou mais campos estão inválidos. Realize corretamente o preenchimento e tente novamente.");
		problem.setDataHora(LocalDateTime.now());
		problem.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
}
