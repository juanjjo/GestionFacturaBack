package com.prueba.factura.invoice.infrestructure.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prueba.factura.product.infrestructure.dto.ProductDto;



@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DetailDto {
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("cantidad")
	private Integer amount;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
	
}
