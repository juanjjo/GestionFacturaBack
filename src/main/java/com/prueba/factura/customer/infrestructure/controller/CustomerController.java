package com.prueba.factura.customer.infrestructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.factura.customer.domain.service.impl.CustomerServiceImpl;
import com.prueba.factura.customer.infrestructure.dto.CustomerDto;

@RestController
@RequestMapping("/cliente")
public class CustomerController {
	
	
	@Autowired
	private CustomerServiceImpl customerService;	
	

	
	@RequestMapping(value="{id}")
	public ResponseEntity<CustomerDto> findOne(@PathVariable("id") Long customerId){
		CustomerDto customerDto = new CustomerDto();
		customerDto  = customerService.getOne(customerId);
		if(customerDto != null) {
			return ResponseEntity.ok(customerDto);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customer){
		CustomerDto customerDto = customerService.createOne(customer);
		return ResponseEntity.ok(customerDto);
	}
	
	
}
