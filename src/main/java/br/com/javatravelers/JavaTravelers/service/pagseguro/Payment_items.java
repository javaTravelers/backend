package br.com.javatravelers.JavaTravelers.service.pagseguro;

import io.swagger.annotations.ApiModelProperty;

public class Payment_items {

	@ApiModelProperty(value = "Id", required = true, example = "1")
	private Integer id;
	
	@ApiModelProperty(value = "Descrição do item", required = true, example = "Passagem aérea para Holanda")
	private String description;
	
	@ApiModelProperty(value = "Valor unitário", required = true, example = "1350.00")
	private String amount;
	
	@ApiModelProperty(value = "Quantidade", required = true, example = "2")
	private Integer quantity;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
