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
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class PaymentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private Integer id;
	
	@NotBlank
	@Column(name = "cod_transacao")
	@ApiModelProperty(value = "Código da Transação", required = true, example = "FFD442271D1DD97CC4D36FA6E093A07D")
	private String codTransacao;
	
	@NotBlank
	@ApiModelProperty(value = "URL para Pagamento", required = true, example = "https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=FFD442271D1DD97CC4D36FA6E093A07D")
	private String url;
	
	@NotNull
	@Column(name = "valor_transacao")
	
	@ApiModelProperty(value = "Valor da Transação", required = true, example = "1000.00")
	private Double valorTransacao;
	
	@NotNull
	@Column(name = "created_at")
	@ApiModelProperty(value = "Data da Geração da Transação", required = true, example = "2020-11-09 16:04:57")
	private Date dataTransacao;
	
	@NotNull
	@Column(name = "updated_at")
	@ApiModelProperty(value = "Data da Última Atualização", required = true, example = "2020-11-09 16:04:57")
	private Date dataStatus;
	
	@NotNull
	@Column(name = "user_id")
	@ApiModelProperty(value = "Código do Usuário", required = true, example = "1")
	private Integer userId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Status da Transação", required = true, example = "APROVADO")
	private PaymentStatus Status;
	
}
