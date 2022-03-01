package com.prueba.factura.customer.infrestructure.mappers;

import org.mapstruct.Mapper;

import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.infrestructure.dto.CustomerAddDto;

@Mapper(
		componentModel = "spring"
)
public interface MapAddCustomer {

	
	public CustomerAddDto  toCustomerAddDto (Customer customer);
	public Customer  toCustomer (CustomerAddDto customerDto);
}
