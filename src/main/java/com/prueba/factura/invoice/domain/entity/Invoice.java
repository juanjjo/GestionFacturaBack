package com.prueba.factura.invoice.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prueba.factura.customer.domain.entity.Customer;




@Entity
@Table(name = "factura")
public class Invoice {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "folio",nullable = false)
	private Long folio ;
	
	@Column(name = "descripcion" ,  length = 45)
	private String description;
	
	@Column(name = "observacion" , length = 45)
	private String observation;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Customer customer;
	
	
	@OneToMany(mappedBy = "invoice", cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<Detail> details = new ArrayList<Detail>();
	
	public Invoice() {
		
	}

	public Invoice(Long id, Long folio, String description, String observation, LocalDate fecha, Customer customer,
			List<Detail> details) {
		super();
		this.id = id;
		this.folio = folio;
		this.description = description;
		this.observation = observation;
		this.fecha = fecha;
		this.customer = customer;
		this.details = details;
	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", folio=" + folio + ", description=" + description + ", observation="
				+ observation + ", fecha=" + fecha + ", customer=" + customer + ", details=" + details + "]";
	}


}
