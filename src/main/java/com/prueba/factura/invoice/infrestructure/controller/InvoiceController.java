package com.prueba.factura.invoice.infrestructure.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.factura.invoice.application.service.impl.InvoiceServiceImpl;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceReadDto;


@RestController
@RequestMapping("/factura")
public class InvoiceController {
	
	@Autowired
	InvoiceServiceImpl invoiceServ;
	
	
	/**
	 * aun por terminar
	 * @param invoiceAdd
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<InvoiceDto> saveInvoice(@RequestBody InvoiceDto invoiceAdd){
		InvoiceDto invoiceDto = this.invoiceServ.createOne(invoiceAdd);
		if(invoiceDto!=null) {
			return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * delete una factura
	 * @return
	 */
	@DeleteMapping(value="{id}")
	public ResponseEntity<InvoiceDto> deleteOne(@PathVariable("id") Long invoiceId){
		InvoiceDto invoceDelete = new InvoiceDto();
		invoceDelete = this.invoiceServ.deleteOne(invoiceId);
		return ResponseEntity.ok(invoceDelete);
	}
	
	/**
	 * recurso actualiza una factura
	 */
	 @PutMapping(value="{id}")
		public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable("id") long id,@RequestBody InvoiceDto invoiceDto){
		 InvoiceDto invoiceResp = new InvoiceDto();
		 invoiceResp = this.invoiceServ.updateInvoice(id,invoiceDto);
			if(invoiceResp == null) {
				return ResponseEntity.noContent().build();
			}else {
				return ResponseEntity.ok(invoiceResp);
			}
		}
	//////////////////////////////////////////// terminados ////////////////////////////
	
	 /**
		 * recurso get Facturas
		 * @return
		 */
		@RequestMapping
		@ResponseBody
		public ResponseEntity<List<InvoiceReadDto>> findAll(){
			List<InvoiceReadDto> invoiceDtos = new ArrayList<InvoiceReadDto>();
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
	public ResponseEntity<List<InvoiceReadDto>> findPrueba(@RequestParam String name){
		List<InvoiceReadDto> invoiceDtos = new ArrayList<InvoiceReadDto>();
		
		invoiceDtos  = invoiceServ.getByCustomer(name);
		return ResponseEntity.ok(invoiceDtos);
		
	}
	
	@RequestMapping(path = "/listar/porFecha")
	@ResponseBody
	public ResponseEntity<List<InvoiceReadDto>> findByFechas(
			 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate 
		       desde,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate 
		       hasta){

		List<InvoiceReadDto> invoiceDtos = new ArrayList<InvoiceReadDto>();	
		invoiceDtos  = invoiceServ.getAllByDate(desde,hasta);
		return ResponseEntity.ok(invoiceDtos);
		
	}
	

	@RequestMapping(value="/list/{id}")
	public ResponseEntity<InvoiceDto> findOne(@PathVariable("id") Long invoiceId){
		InvoiceDto bedroomDto = new InvoiceDto();
		bedroomDto  = invoiceServ.getOne(invoiceId);
		if(bedroomDto != null) {
			return ResponseEntity.ok(bedroomDto);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	
	
	

	
	
	
}












