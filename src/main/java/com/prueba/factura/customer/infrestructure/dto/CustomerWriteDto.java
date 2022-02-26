package com.prueba.factura.customer.infrestructure.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerWriteDto {
	@NotNull
	@JsonProperty("name")
	private String nameCustomer;
	
	@NotNull
	@JsonProperty("lastName")
	private String lastNameCustomer;
	
	@NotNull
	@JsonProperty("eMail")
	private String eMailCustomer;


	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getLastNameCustomer() {
		return lastNameCustomer;
	}

	public void setLastNameCustomer(String lastNameCustomer) {
		this.lastNameCustomer = lastNameCustomer;
	}

	public String geteMailCustomer() {
		return eMailCustomer;
	}

	public void seteMailCustomer(String eMailCustomer) {
		this.eMailCustomer = eMailCustomer;
	}
}
