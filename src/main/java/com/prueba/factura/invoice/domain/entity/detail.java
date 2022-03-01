package com.prueba.factura.invoice.domain.entity;

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

import com.prueba.factura.product.domain.entity.Product;

@Entity
@Table(name = "detalle")
public class Detail {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cantidad")
	private Integer amount;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "factura_id")
	private Invoice invoice;
	

	
	public Detail() {
		// TODO Auto-generated constructor stub
	}

	public Detail(Long id, Integer amount, Invoice invoice) {
		super();
		this.id = id;
		this.amount = amount;
		this.invoice = invoice;
	}

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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}



	@Override
	public String toString() {
		return "Detail [id=" + id + ", amount=" + amount + ", invoice=" + invoice + "]";
	}

	
	
}
