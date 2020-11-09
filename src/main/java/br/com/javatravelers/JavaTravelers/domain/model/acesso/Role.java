package br.com.javatravelers.JavaTravelers.domain.model.acesso;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role {
	@Id
	@Column(length = 20)
	private String role;
	
	public Role() {}
	
	public Role(String role) {
		this.role = role;
	}
}