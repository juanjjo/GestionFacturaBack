package com.prueba.factura.invoice.infrestructure.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prueba.factura.customer.infrestructure.dto.CustomerDto;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class InvoiceReadDto {
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
	private String nameCustomer;
	
	
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

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	
	
}
