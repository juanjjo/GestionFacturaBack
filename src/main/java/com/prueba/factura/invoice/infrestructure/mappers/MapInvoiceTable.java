package com.prueba.factura.invoice.infrestructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceTableDto;



@Mapper(
		componentModel = "spring",
		uses = {MapCustomer.class}
)
public interface MapInvoiceTable {
	
	@Mapping(source  = "invoice.customer.nameCustomer", target = "nameCustomer")
	
	public InvoiceTableDto toInvoiceTableDto(Invoice invoice);
	
	
	public List<InvoiceTableDto> toInvoiceTableDtos (List<Invoice> invoices);
}
