package com.prueba.factura.invoice.infrestructure.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.factura.invoice.application.service.impl.InvoiceServiceImpl;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceTableDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceWriteDto;


@RestController
@RequestMapping("/factura")
public class InvoiceController {
	
	@Autowired
	InvoiceServiceImpl invoiceServ;
	
	
	
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
	
	/**
	 * aun por terminar
	 * @param invoiceAdd
	 * @return
	 */
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
	
	
	/**
	 * recurso get Facturas
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public ResponseEntity<List<InvoiceTableDto>> findAll(){
		List<InvoiceTableDto> invoiceDtos = new ArrayList<InvoiceTableDto>();
		invoiceDtos = this.invoiceServ.getAll();
		return ResponseEntity.ok(invoiceDtos);
	}
	
	/**
	 * recurso get facturas por nombre de cliente
	 * @param name
	 * @return
	 */
	@RequestMapping(path = "/listar/porCliente")
	@ResponseBody
	public ResponseEntity<List<InvoiceTableDto>> findPrueba(@RequestParam String name){
		List<InvoiceTableDto> invoiceDtos = new ArrayList<InvoiceTableDto>();
		
		invoiceDtos  = invoiceServ.getByCustomer(name);
		return ResponseEntity.ok(invoiceDtos);
		
	}
	
	
	
}












