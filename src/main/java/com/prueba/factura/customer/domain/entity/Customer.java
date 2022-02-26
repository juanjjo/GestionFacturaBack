package com.prueba.factura.customer.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Customer {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "nombre",nullable = false, length = 45)
	private String nameCustomer;
	
	@Column(name = "apellido", nullable = false, length = 45)
	private String lastNameCustomer;
	
	@Column(name = "email" , nullable = false, length = 45)
	private String eMailCustomer;
	
	public  Customer() {
		
	}

	public Customer(Long id, String nameCustomer, String lastNameCustomer, String eMailCustomer) {
		super();
		this.id = id;
		this.nameCustomer = nameCustomer;
		this.lastNameCustomer = lastNameCustomer;
		this.eMailCustomer = eMailCustomer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "CustomerService [id=" + id + ", nameCustomer=" + nameCustomer + ", lastNameCustomer=" + lastNameCustomer
				+ ", eMailCustomer=" + eMailCustomer + "]";
	}
	
}


