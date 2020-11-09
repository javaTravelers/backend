package br.com.javatravelers.JavaTravelers.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.javatravelers.JavaTravelers.domain.enums.PaymentStatus;
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
	
	@NotNull
	@Column(name = "valor_transacao")
	private Double valorTransacao;
	
	@NotNull
	@Column(name = "created_at")
	private Date dataTransacao;
	
	@NotNull
	@Column(name = "updated_at")
	private Date dataStatus;
	
	@NotNull
	@Column(name = "user_id")
	private Integer userId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PaymentStatus Status;
	
}
