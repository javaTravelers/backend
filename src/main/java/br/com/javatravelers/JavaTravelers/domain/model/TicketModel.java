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
	@ApiModelProperty(value = "Código do Ticket", required = true, example = "1")
	private Integer id;
	
	@NotBlank
	@Column(columnDefinition = "LONGTEXT", name = "flight_get")
	@ApiModelProperty(value = "String Com a Passagem Reservada", required = true, example = "1")
	private String flightGet;
	
	@Column(columnDefinition = "LONGTEXT", name = "flight_result")
	@ApiModelProperty(value = "String Com a Passagem Emitida", required = true, example = "1")
	private String flightResult;
	
	@Column(name = "user_id")
	@ApiModelProperty(value = "Código do Usuário", required = true, example = "1")
	private Integer userId;
		
	@Column(name = "referencia_reserva")
	@ApiModelProperty(value = "Código da Reserva para Embarque", required = true, example = "N3R8E7")
	private String reference;
	
	@Column(name = "id_reserva")
	@ApiModelProperty(value = "ID da Reserva Para Cancelamento e Consulta", required = true, example = "eJzTd9f3M45wNXUBAArhAj0%3D")
	private String reservationId;
	
	@NotBlank
	@Column(name = "payment_url")
	@ApiModelProperty(value = "URL para Pagamento", required = true, example = "https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=FFD442271D1DD97CC4D36FA6E093A07D")
	private String paymentUrl;
	
	@Column(name = "payment_id")
	@ApiModelProperty(value = "Código da Transação", required = true, example = "FFD442271D1DD97CC4D36FA6E093A07D")
	private String paymentId;
	
	@NotNull
	@ApiModelProperty(value = "Valor da Compra", required = true, example = "1000.00")
	private Double price;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Status da Passagem", required = true, example = "RESERVADO")
	private TicketStatus status;
}
