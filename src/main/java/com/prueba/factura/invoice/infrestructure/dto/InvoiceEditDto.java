package com.prueba.factura.invoice.infrestructure.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prueba.factura.customer.infrestructure.dto.CustomerDto;

public class InvoiceEditDto {
	@JsonProperty("id")
    private Long id;
	
	@NotNull
	@JsonProperty("folio")
	private Long folio;
	
	@NotNull
	@JsonProperty("descripcion")
	private String description;

	@NotNull
	@JsonProperty("observacion")
	private String observation;
	
	
	@NotNull
	@JsonProperty("fecha")
	private LocalDate fecha;

	@NotNull
	@JsonProperty("cliente")
	private CustomerDto customerDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
}
