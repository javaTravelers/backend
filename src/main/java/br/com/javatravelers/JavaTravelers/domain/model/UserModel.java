package br.com.javatravelers.JavaTravelers.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserModel implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message="O campo nome deve ser preenchido.")
	@Size(max = 80, message = "O nome deve conter no máximo 50 caracteres.")
	@Column(length = 80, nullable = false)
	private String nome;
	
	@Email(message = "O endereço de e-mail é inválido!")
	@NotBlank(message = "O campo e-mail deve ser preenchido.")
	@Size(max = 60, message = "O endereço de e-mail é muito grande.")
	@Column(length = 60, nullable = false)
	private String email;
	
	@NotBlank(message = "O campo senha deve ser preenchido.")
	@Size(max = 20, message = "A senha deve conter no máximo 20 caracteres.")
	@Column(length = 20, nullable = false)
	private String senha;
	
	@NotBlank(message = "O campo cpf deve ser preenchido.")
	@Pattern(regexp = "[0-9]{11}", message = "Preencha com um cpf válido!")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	
}
