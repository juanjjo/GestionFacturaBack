package com.prueba.factura.invoice.application.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.application.service.impl.CustomerServiceImpl;
import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.domain.repository.CustomerRepository;
import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.application.service.InvoiceService;
import com.prueba.factura.invoice.domain.entity.Detail;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.domain.repository.DetailRepository;
import com.prueba.factura.invoice.domain.repository.InvoiceRepository;
import com.prueba.factura.invoice.infrestructure.dto.DetailDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceReadDto;
import com.prueba.factura.invoice.infrestructure.mappers.MapDetail;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoice;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceAdd;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceRevers;
import com.prueba.factura.product.infrestructure.mappers.MapProduct;




@Service
public class InvoiceServiceImpl implements InvoiceService{
	

	@Autowired
	private InvoiceRepository invoiceDao;
	@Autowired
	private DetailRepository detailDao;
	@Autowired
	private MapInvoice mapInvoice;
	@Autowired
	private MapDetail mapDetail;
	@Autowired
	private MapProduct  mapPoruct;
	@Autowired
	private MapInvoiceAdd mapInvoiceAdd;
	@Autowired
	private MapInvoiceRevers mapInvoiceRever;
	@Autowired
	private MapCustomer mapCustomer;
	
	@Autowired
	private CustomerRepository customerRep;

	
	/**
	 * todabia crea uno solo.. falta aun q cree, le falta odio
	 */
	@Override
	public InvoiceDto createOne(InvoiceDto invoiceDto) {
		Invoice invoice = new Invoice();
		Customer customer = new Customer();
		List<Detail> details = new ArrayList<Detail>();
		customer = customerRep.findByEmail(invoiceDto.getCustomerDto().geteMailCustomer()).orElse(null);
		details = mapDetail.toDetails(invoiceDto.getDetailDtos());
		if (customer != null) {
			return null;
		}
		System.out.println("detailles: "+details);
		customer = mapCustomer.toCustomer(invoiceDto.getCustomerDto());
		invoice = mapInvoiceRever.toInvoice(invoiceDto);
		invoice.setDetails(details);	
		
		for (Detail detail : details) {
			detail.setInvoice(invoice);
			for (DetailDto detailDto : invoiceDto.getDetailDtos()) {
				detail.setProduct(mapPoruct.toProduct(detailDto.getProductDto()));
			}
		}
		try {
			invoice=this.invoiceDao.save(invoice);
		} catch (Exception e) {
			System.out.println("error save");
		}

//		System.err.println("detalles: "+details);
		//return mapInvoiceAdd.toInvoiceDto(invoice);
		return null;
	}
	
	@Override
	public InvoiceDto deleteOne(Long id) {
		Invoice invoice = new Invoice();
		invoice = this.invoiceDao.findById(id).orElse(null);
		if(invoice==null) {
			return null;
		}
	
		this.invoiceDao.delete(invoice);
		return mapInvoiceAdd.toInvoiceDto(invoice);
	}

	
	@Override
	public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDto) {
		Customer customerUpdate = new Customer();
		Invoice invoice = new Invoice();
		Invoice invoiceUpdate = new Invoice();
		invoice = this.invoiceDao.findById(id).orElse(null);
		
		if(invoice ==null) {
			return null;
		}
		invoiceUpdate  = mapInvoiceRever.toInvoice(invoiceDto);
		customerUpdate = invoiceUpdate.getCustomer();
		customerUpdate.setId(invoice.getCustomer().getId());
		
		invoice = invoiceUpdate;
		invoice.setId(id);
		invoice.setCustomer(customerUpdate);
		this.invoiceDao.save(invoice);
		return mapInvoiceAdd.toInvoiceDto(invoice);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * busquedas terminada de aqui ne adelante
	 */
	
	/**
	 * busqueda de factura por nombre patron nombre cliente Finalizado
	 */
	@Override
	public List<InvoiceReadDto> getByCustomer(String nameCustomer) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByCustemer(nameCustomer);
		//System.out.println("invoices: "+ invoices);
		return mapInvoice.toDtos(invoices);
	}

	
	/**
	 * retorna todos las facturas con formato
	 */
	@Override
	public List<InvoiceReadDto> getAll() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = this.invoiceDao.findAll();
		return mapInvoice.toDtos(invoices);
	}

	
	@Override
	public List<InvoiceReadDto> getAllByDate(LocalDate desde,LocalDate hasta) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByBeetwenFecha(desde, hasta);
		return mapInvoice.toDtos(invoices);
	}


}



































