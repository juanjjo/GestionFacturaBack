package com.prueba.factura.customer.domain.service;

import com.prueba.factura.customer.infrestructure.dto.CustomerDto;
import com.prueba.factura.customer.infrestructure.dto.CustomerAddDto;


public interface CustomerService {
	public CustomerDto getOne(Long id);
	
	public CustomerDto createOne(CustomerDto customerDto);
	
}
