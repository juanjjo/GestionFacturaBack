package com.prueba.factura.customer.application.service;

import com.prueba.factura.customer.infrestructure.dto.CustomerDto;
import com.prueba.factura.customer.infrestructure.dto.CustomerWriteDto;


public interface CustomerService {
	public CustomerDto getOne(Long id);
	
	public CustomerDto createOne(CustomerWriteDto customerDto);
	
}
