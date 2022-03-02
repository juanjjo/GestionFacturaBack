package com.prueba.factura.invoice.infrestructure.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
@Mapper(
		componentModel = "spring",
		uses = {MapCustomer.class,MapDetail.class}
)
public interface MapInvoiceAdd {

	
	@Mapping(source  = "invoice.customer", target = "customerDto")
	
	
	
	public InvoiceDto  toInvoiceDto (Invoice invoice);
	

}
