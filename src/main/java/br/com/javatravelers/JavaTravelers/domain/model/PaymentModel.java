package br.com.javatravelers.JavaTravelers.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.javatravelers.JavaTravelers.domain.status.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class PaymentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name = "cod_transacao")
	private String codTransacao;
	
	@NotBlank
	private String url;
	
	@NotBlank
	@Column(name = "valor_transacao")
	private Double valorTransacao;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private PaymentStatus Status;
	
}
