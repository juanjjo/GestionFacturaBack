package com.prueba.factura.invoice.infrestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;





@Mapper(
		componentModel = "spring",
		uses = {MapCustomer.class}
)
public interface MapInvoiceAdd {
	
	
	InvoiceDto  toInvoiceDto (InvoiceWriteDto invoice);
	Invoice toInvoice (InvoiceWriteDto invoiceAdd); 
	
}
