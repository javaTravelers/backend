package br.com.javatravelers.JavaTravelers.domain.model.acesso;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user_auth")
public class UserAuthModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 70)
	private String login;
	
	@NotBlank(message = "O campo senha deve ser preenchido.")
	@Size(max = 100, message = "A senha deve conter no máximo 20 caracteres.")
	@Column(length = 100, nullable = false)
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "login",nullable=false), inverseJoinColumns = @JoinColumn(name = "role",nullable=false))
	private Set<Role> roles = new HashSet<>();
	
	
	@NotBlank(message="O campo nome deve ser preenchido.")
	@Size(max = 80, message = "O nome deve conter no máximo 50 caracteres.")
	@Column(length = 80, nullable = false)
	private String nome;
	
	@Email(message = "O endereço de e-mail é inválido!")
	@NotBlank(message = "O campo e-mail deve ser preenchido.")
	@Size(max = 60, message = "O endereço de e-mail é muito grande.")
	@Column(length = 60, nullable = false)
	private String email;
	
	@NotBlank(message = "O campo cpf deve ser preenchido.")
	@Pattern(regexp = "[0-9]{11}", message = "Preencha com um cpf válido!")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
}
