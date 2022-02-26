package com.prueba.factura.invoice.infrestructure.mappers;

import org.mapstruct.Mapper;

import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;





@Mapper(
		componentModel = "spring",
		uses = {MapCustomer.class}
)
public interface MapInvoiceAdd {
	
	Invoice toInvoice (InvoiceWriteDto invoiceAdd); 
	
}
