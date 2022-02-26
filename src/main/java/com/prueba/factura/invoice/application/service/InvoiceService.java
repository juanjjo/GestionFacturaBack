package com.prueba.factura.invoice.application.service;

import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;



public interface InvoiceService	 {
	
	public InvoiceDto getOne(Long id);
	
	public InvoiceDto createOne(InvoiceWriteDto invoiceDto);
	
	public InvoiceDto getOneByCustomer(Long idCustomer);
}
