package com.prueba.factura.customer.infrestructure.mappers;

import org.mapstruct.Mapper;

import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.infrestructure.dto.CustomerDto;
import com.prueba.factura.customer.infrestructure.dto.CustomerWriteDto;




@Mapper(
		componentModel = "spring"
)
public interface MapCustomer {
	
	public CustomerDto  toCustomerDto (Customer customer);
	public Customer  toCustomer (CustomerWriteDto customerDto);
}
