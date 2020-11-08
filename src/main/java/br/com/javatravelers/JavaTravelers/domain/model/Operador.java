package br.com.javatravelers.JavaTravelers.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.UserAuthModel;

@Entity
@Table(name = "tab_operador")
public class Operador extends UserAuthModel {
	
	@Column(length = 60)
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
