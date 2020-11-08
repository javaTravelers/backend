package br.com.javatravelers.JavaTravelers.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	@Column(columnDefinition = "LONGTEXT")
	private String flight;
	
	@Column(name = "user_id")
	private Integer userId;
		
	@Column(name = "numero_reserva")
	private String numeroReserva;
	
	@NotBlank
	@Column(name = "payment_url")
	private String paymentUrl;
	
	@NotBlank
	private String status;
	
	@Column(name = "payment_id")
	private String paymentId;
}
