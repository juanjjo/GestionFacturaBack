package com.prueba.factura.invoice.application.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.domain.repository.CustomerRepository;
import com.prueba.factura.invoice.application.service.InvoiceService;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.domain.repository.InvoiceRepository;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceTableDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoice;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceAdd;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceTable;




@Service
public class InvoiceServiceImpl implements InvoiceService{
	

	@Autowired
	private InvoiceRepository invoiceDao;
	
	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private MapInvoice mapInvoice;
	
	@Autowired
	private MapInvoiceAdd mapAddInvoice;
	
	@Autowired
	private MapInvoiceTable mapTableInvoice;
	
	
	/**
	 * get One auxiliar
	 */
	@Override
	public InvoiceDto getOne(Long id) {
		Invoice invoice = new Invoice();
		invoice = invoiceDao.getById(id);
		if(invoice == null) {
			return null;
		}
		return mapInvoice.toInvoiceDto(invoice);
	}

	/**
	 * todabia crea uno solo.. falta aun q cree
	 */
	@Override
	public InvoiceDto createOne(InvoiceWriteDto invoiceDto) {
		Invoice invoice = new Invoice();
		Customer customer = new Customer();
		customer = this.customerDao.getById(invoiceDto.getIdCustomer());
		if(customer == null) {
			return null;
		}
		invoice=this.mapAddInvoice.toInvoice(invoiceDto);
		invoice.setCustomer(customer);
		this.invoiceDao.save(invoice);
		return this.mapInvoice.toInvoiceDto(invoice);
	}

	
	
	/**
	 * busqueda de factura por nombre patron nombre cliente Finalizado
	 */
	@Override
	public List<InvoiceTableDto> getByCustomer(String nameCustomer) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByCustemer(nameCustomer);
		//System.out.println("invoices: "+ invoices);
		return mapTableInvoice.toInvoiceTableDtos(invoices);
	}

	
	/**
	 * retorna todos las facturas con formato
	 */
	@Override
	public List<InvoiceTableDto> getAll() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = this.invoiceDao.findAll();
		return mapTableInvoice.toInvoiceTableDtos(invoices);
	}

	
	@Override
	public List<InvoiceTableDto> getAllByDate(LocalDate desde,LocalDate hasta) {
		 //SimpleDateFormat formater = new SimpleDateFormat("2022-02-23");
		 //System.out.println(formater);
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByBeetwenFecha(desde, hasta);
		return mapTableInvoice.toInvoiceTableDtos(invoices);
	}


}



































