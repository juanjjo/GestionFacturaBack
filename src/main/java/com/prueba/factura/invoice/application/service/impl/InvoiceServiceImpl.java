package com.prueba.factura.invoice.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.domain.repository.CustomerRepository;
import com.prueba.factura.invoice.application.service.InvoiceService;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.domain.repository.InvoiceRepository;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoice;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceAdd;




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
	
	@Override
	public InvoiceDto getOne(Long id) {
		Invoice invoice = new Invoice();
		invoice = invoiceDao.getById(id);
		if(invoice == null) {
			return null;
		}
		return mapInvoice.toCustomerDto(invoice);
	}

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
		return this.mapInvoice.toCustomerDto(invoice);
	}

	@Override
	public InvoiceDto getOneByCustomer(Long idCustomer) {
		List<Invoice> invoices = new ArrayList();
		invoices = invoiceDao.getByCustemer(idCustomer);
//		if(invoice == null) {
//			return null;
//		}
		//return mapInvoice.toCustomerDto(invoice);
		return null;
	}

}



































