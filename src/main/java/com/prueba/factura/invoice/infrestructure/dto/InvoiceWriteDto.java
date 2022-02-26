package com.prueba.factura.invoice.infrestructure.dto;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceWriteDto {
	@NotNull
	@JsonProperty("folio")
	private String folio;
	
	@NotNull
	@JsonProperty("descripcion")
	private String description;
	
	@NotNull
	@JsonProperty("observacion")
	private String observation;
	
	
	@NotNull
	@JsonProperty("fecha")
	private Calendar fecha;

	@NotNull
	@JsonProperty("idCustomer")
	private Long idCustomer;


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

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	
	
}
