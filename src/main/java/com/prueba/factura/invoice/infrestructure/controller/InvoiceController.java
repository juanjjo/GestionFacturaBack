package com.prueba.factura.invoice.infrestructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.factura.invoice.application.service.impl.InvoiceServiceImpl;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;


@RestController
@RequestMapping("/factura")
public class InvoiceController {
	
	@Autowired
	InvoiceServiceImpl invoiceServ;
	
	
	 @GetMapping(path = "/helloType")
	    public String helloType(){
	        return "Hola Typo";
	    }
//	@PostMapping
//	public ResponseEntity<InvoiceDto> Invoice(@RequestBody CustomerWriteDto customer){
//		CustomerDto customerDto = customerService.createOne(customer);
//		return ResponseEntity.ok(customerDto);
//	}
	
//	@RequestMapping(value="{id}")
//	public ResponseEntity<InvoiceDto> findOne(@PathVariable("id") Long invoiceId){
//		InvoiceDto invoiceDto = new InvoiceDto();
//		invoiceDto  = invoiceServ.getOne(invoiceId);
//		System.out.println(invoiceDto);
//		if(invoiceDto != null) {
//			return ResponseEntity.ok(invoiceDto);
//		}else {
//			return ResponseEntity.noContent().build();
//		}
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<InvoiceDto> saveInvoice(@RequestBody InvoiceWriteDto invoiceAdd){
		InvoiceDto invoiceDto = this.invoiceServ.createOne(invoiceAdd);
		System.out.println("invoiceDto: "+invoiceDto);
		if(invoiceDto!=null) {
			return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value="{id}")
	public ResponseEntity<InvoiceDto> findPrueba(@PathVariable("id") Long invoiceId){
		InvoiceDto invoiceDto = new InvoiceDto();
		invoiceDto  = invoiceServ.getOneByCustomer(invoiceId);
		System.out.println(invoiceDto);
		if(invoiceDto != null) {
			return ResponseEntity.ok(invoiceDto);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
}
