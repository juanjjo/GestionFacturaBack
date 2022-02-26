package com.prueba.factura.invoice.infrestructure.dto;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceTableDto {
	@JsonProperty("id")
    private Long id;
	@NotNull
	
	@JsonProperty("folio")
	private String folio;
	
	@NotNull
	@JsonProperty("descripcion")
	private String description;
	
	
	@NotNull
	@JsonProperty("fecha")
	private Calendar fecha;
	
	@NotNull
	@JsonProperty("nameCustomer")
	private String nameCustomer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	
	
}
