package com.prueba.factura.invoice.application.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceReadDto;



public interface InvoiceService	 {
	
	

	
	public List<InvoiceReadDto> getByCustomer(String nameCustomer);
	
	public List<InvoiceReadDto> getAllByDate(LocalDate desde, LocalDate hasta);
	
	public List<InvoiceReadDto> getAll ();
	
	
	public InvoiceDto createOne(InvoiceDto invoiceDto);
	
	public InvoiceDto deleteOne(Long id);
	
	public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDto);
	
	InvoiceDto getOne(Long id);
}
