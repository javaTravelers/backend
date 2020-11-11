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
@Table(name = "favorite")
public class FavoriteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Código do Favorito")
	private Integer id;
	
	@NotBlank
	@ApiModelProperty(value = "", example = "Flight")
	@Column(columnDefinition = "LONGTEXT")
	private String flight;
	
	
	@ApiModelProperty(value = "Id do usuário", example = "1")
	@Column(name = "user_id")
	private Integer userId;
	
}
