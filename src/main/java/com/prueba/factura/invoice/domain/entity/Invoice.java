package com.prueba.factura.invoice.domain.entity;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Customer customer;
	
	public Invoice() {
		
	}

	public Invoice(Long id, Long folio, String description, String observation, Calendar fecha, Customer customer) {
		super();
		this.id = id;
		this.folio = folio;
		this.description = description;
		this.observation = observation;
		this.fecha = fecha;
		this.customer = customer;
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

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", folio=" + folio + ", description=" + description + ", observation="
				+ observation + ", fecha=" + fecha + ", customer=" + customer + "]";
	}

	

}
