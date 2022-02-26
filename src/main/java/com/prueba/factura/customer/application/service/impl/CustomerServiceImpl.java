package com.prueba.factura.customer.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.application.service.CustomerService;
import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.domain.repository.CustomerRepository;
import com.prueba.factura.customer.infrestructure.dto.CustomerDto;
import com.prueba.factura.customer.infrestructure.dto.CustomerWriteDto;
import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerDao;
	
	@Autowired
	private MapCustomer mapCustomer;

	
	
	@Override
	public CustomerDto getOne(Long id) {
		Customer foundCustomer = new Customer();
		foundCustomer = this.customerDao.getById(id);
		if(foundCustomer==null) {
			return null;
		}
		
		return mapCustomer.toCustomerDto(foundCustomer);
	}

	@Override
	public CustomerDto createOne(CustomerWriteDto customerDto) {
		Customer customer = new Customer();
		customer = this.mapCustomer.toCustomer(customerDto);
		this.customerDao.save(customer);
		return this.mapCustomer.toCustomerDto(customer);
	}

	

	
}










