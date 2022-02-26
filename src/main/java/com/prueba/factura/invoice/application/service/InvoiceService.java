package com.prueba.factura.invoice.application.service;

import java.util.List;

import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceTableDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;



public interface InvoiceService	 {
	
	public InvoiceDto getOne(Long id);
	
	public InvoiceDto createOne(InvoiceWriteDto invoiceDto);
	
	public List<InvoiceTableDto> getByCustomer(String nameCustomer);
	
	public List<InvoiceTableDto> getAll ();
	
}
