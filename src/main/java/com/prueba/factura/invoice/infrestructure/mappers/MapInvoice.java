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
public interface MapInvoice {
	
	
	@Mapping(source  = "invoice.customer", target = "customerDto")

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customerDto.id", ignore = true)
	@Mapping(target = "customerDto.lastNameCustomer", ignore = true)
	@Mapping(target = "customerDto.eMailCustomer", ignore = true)
	public InvoiceDto  toInvoiceDto (Invoice invoice);
	
	public List<InvoiceDto> toDtos (List<Invoice> invoices);
	
	
	
}
