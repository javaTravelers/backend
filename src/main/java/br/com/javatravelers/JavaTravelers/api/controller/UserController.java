package br.com.javatravelers.JavaTravelers.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserModel;
import br.com.javatravelers.JavaTravelers.domain.repository.UserRepository;
import br.com.javatravelers.JavaTravelers.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(value = "Usuário")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@ApiOperation(value = "Salvar novo usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Requisição bem sucedida e o novo usuário criado com resultado."),
			@ApiResponse(code = 400, message = "Uns dos campos do cadastro não foi preenchido, ou preenchido incorretamente."),
			@ApiResponse(code = 422, message = "Cpf inválido ou e-mail já existente."),
	})
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserModel userModel) {
		return new ResponseEntity<UserModel>(userService.saveUser(userModel), HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Excluir usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Requisição bem sucedida e usuário excluído com sucesso."),
			@ApiResponse(code = 404, message = "Usuário não encontrado.")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		
		if(!userRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	
}

