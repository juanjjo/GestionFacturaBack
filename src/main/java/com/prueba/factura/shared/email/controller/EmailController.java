package com.prueba.factura.shared.email.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.factura.invoice.infrestructure.dto.InvoiceReadDto;
import com.prueba.factura.shared.email.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	EmailService emailService;
	
	@RequestMapping(path = "/send")
	@ResponseBody
	public String findPrueba(){
		List<InvoiceReadDto> invoiceDtos = new ArrayList<InvoiceReadDto>();
		
		emailService.sendMail("ajota.3794@gmail.com", "asunto", "contenido");
		return "enviado succesfull";
		
	}
}
