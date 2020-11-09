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
import javax.validation.constraints.NotNull;

import br.com.javatravelers.JavaTravelers.domain.enums.TicketStatus;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "Código do Ticket")
	private Integer id;
	
	@NotBlank
	@Column(columnDefinition = "LONGTEXT", name = "flight_get")
	private String flightGet;
	
	@Column(columnDefinition = "LONGTEXT", name = "flight_result")
	private String flightResult;
	
	@Column(name = "user_id")
	private Integer userId;
		
	@Column(name = "referencia_reserva")
	private String reference;
	
	@Column(name = "id_reserva")
	private String reservationId;
	
	@NotBlank
	@Column(name = "payment_url")
	private String paymentUrl;
	
	@Column(name = "payment_id")
	private String paymentId;
	
	@NotNull
	private Double price;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
}
