package br.com.javatravelers.JavaTravelers.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.com.javatravelers.JavaTravelers.domain.status.TicketStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class TicketModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(name = "data_partida")
	private LocalDate dataPartida;
	
	@NotBlank
	@Column(name = "data_retorno")
	private LocalDate dataRetorno;
	
	@NotBlank
	@Column(name = "aeroporto_origem")
	private String aeroportoOrigem;
	
	@NotBlank
	@Column(name = "cidade_origem")
	private String cidadeOrigem;
	
	@NotBlank
	@Column(name = "aeroporto_destino")
	private String aeroportoDestino;
	
	@NotBlank
	@Column(name = "cidade_destino")
	private String cidadeDestino;
		
	@NotBlank
	@Setter(AccessLevel.NONE)
	private LocalDateTime Embarque;
	
	@NotBlank
	@Setter(AccessLevel.NONE)
	private LocalDateTime Chegada;
	
	@NotBlank
	private String portao_embarque;
	
	@NotBlank
	@Column(name = "duracaoVoo")
	private Integer duracaoVoo;
	
	@NotBlank
	private String terminal;
	
	@NotBlank
	private Integer paradas;
	
	@NotBlank
	@Column(name = "numero_voo")
	private String numeroVoo;
	
	@NotBlank
	private Double preco;
	
	@NotBlank
	@Column(name = "preco_moeda")
	private String precoMoeda;
	
	@NotBlank
	@Column(name = "tipo_passageiro")
	private String tipoPassageiro;
	
	@NotBlank
	private String classe;
	
	@NotBlank
	private Integer assento;
	
	@NotBlank
	@Column(name = "inclui_bagagem")
	private boolean incluiBagagem;
	
	@NotBlank
	@Column(name = "numero_reserva")
	private String numeroReserva;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	
	@NotBlank
	private String companhia;
	
	@NotBlank
	@ManyToOne
	private UserModel user;
	
	@NotBlank
	@OneToOne
	private PaymentModel payment;
	
	@SuppressWarnings("unused")
	private void setEmbarque (LocalDateTime embarque) {
		this.Embarque = embarque.withNano(0);
	}
	
	@SuppressWarnings("unused")
	private void setChegada (LocalDateTime chegada) {
		this.Chegada = chegada.withNano(0);
	}
	
}
