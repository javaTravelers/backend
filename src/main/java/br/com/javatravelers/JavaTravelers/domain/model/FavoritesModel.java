package br.com.javatravelers.JavaTravelers.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorite")
public class FavoritesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private LocalDate data;
	
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
	@Column(name = "horario_embarque")
	private String horarioEmbarque;
	
	@NotBlank
	@Column(name = "horarioChegada")
	private String horarioChegada;
	
	@NotBlank
	@Column(name = "duracaoVoo")
	private Integer duracaoVoo;
	
	@NotBlank
	private Double preco;
	
	@NotBlank
	@Column(name = "tipo_passageiro")
	private String tipoPassageiro;
	
	@NotBlank
	@Column(name = "inclui_bagagem")
	private boolean incluiBagagem;
	
	@NotBlank
	private String companhia;
	
	@NotBlank
	@ManyToOne
	private UserModel user;
	
}
