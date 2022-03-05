package com.prueba.factura.invoice.application.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.application.service.impl.CustomerServiceImpl;
import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.customer.domain.repository.CustomerRepository;
import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.application.service.InvoiceService;
import com.prueba.factura.invoice.domain.entity.Detail;
import com.prueba.factura.invoice.domain.entity.Invoice;
import com.prueba.factura.invoice.domain.repository.DetailRepository;
import com.prueba.factura.invoice.domain.repository.InvoiceRepository;
import com.prueba.factura.invoice.infrestructure.dto.DetailDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceDto;
import com.prueba.factura.invoice.infrestructure.dto.InvoiceReadDto;
import com.prueba.factura.invoice.infrestructure.mappers.MapDetail;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoice;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceAdd;
import com.prueba.factura.invoice.infrestructure.mappers.MapInvoiceRevers;
import com.prueba.factura.product.application.Service.ProductService;
import com.prueba.factura.product.domain.entity.Product;
import com.prueba.factura.product.domain.repository.ProductRepository;
import com.prueba.factura.product.infrestructure.mappers.MapProduct;




@Service
public class InvoiceServiceImpl implements InvoiceService{
	

	@Autowired
	private InvoiceRepository invoiceDao;
	@Autowired
	private DetailRepository detailDao;
	@Autowired
	private CustomerRepository customerDao;
	@Autowired
	private ProductRepository producDao;
	
	@Autowired
	private MapInvoice mapInvoice;
	@Autowired
	private MapDetail mapDetail;
	@Autowired
	private MapProduct  mapPoruct;
	@Autowired
	private MapInvoiceAdd mapInvoiceAdd;
	@Autowired
	private MapInvoiceRevers mapInvoiceRever;
	@Autowired
	private MapCustomer mapCustomer;
	
	@Autowired
	private CustomerRepository customerRep;

	
	@Autowired
	private ProductService productServ;
	
	/**
	 * todabia crea uno solo.. falta aun q cree, le falta odio
	 */

	@Override
	public InvoiceDto createOne(InvoiceDto invoiceDto) {
		Invoice invoice = new Invoice();
		Customer customer = new Customer();
		Product pro = new Product();
		List<Detail> details = new ArrayList<Detail>();
		customer = customerRep.findByEmail(invoiceDto.getCustomerDto().geteMailCustomer()).orElse(null);
		details = mapDetail.toDetails(invoiceDto.getDetailDtos());
		invoice = mapInvoiceRever.toInvoice(invoiceDto);
		if (customer != null) {
			invoice.setCustomer(customer);
		}else {
			customer = mapCustomer.toCustomer(invoiceDto.getCustomerDto());
			//this.customerDao.save(customer);
			invoice.setCustomer(customer);
		}		
		
		
		invoice.setDetails(details);	
		
		for (Detail detail : details) {
			detail.setInvoice(invoice);
			for (DetailDto detailDto : invoiceDto.getDetailDtos()) {
				pro=producDao.save(mapPoruct.toProduct(detailDto.getProductDto()));
				detail.setProduct(pro);
			}
		}
		System.out.println("error save");
			invoice=this.invoiceDao.save(invoice);
		 
			
		

//		System.err.println("detalles: "+details);
		//return mapInvoiceAdd.toInvoiceDto(invoice);
		return null;
	}
	
	@Override
	public InvoiceDto deleteOne(Long id) {
		Invoice invoice = new Invoice();
		
		Customer customer = new Customer();
		invoice = this.invoiceDao.findById(id).orElse(null);
		if(invoice==null) {
			return null;
		}
		customer = customerRep.findByEmail(invoice.getCustomer().geteMailCustomer()).orElse(null);
		if(customer!=null) {
			invoice.setCustomer(null);
		}
		
		this.invoiceDao.delete(invoice);
		return mapInvoiceAdd.toInvoiceDto(invoice);
		
	}

	
	/**
	 * actualiza una factura
	 */
	@Override
	public InvoiceDto updateInvoice(Long id, InvoiceDto invoiceDto) {
		Invoice invoiceFound = new Invoice();
		invoiceFound = this.invoiceDao.findById(id).orElse(null);
		Customer cust = new Customer();
		cust = this.customerDao.save(mapCustomer.toCustomer(invoiceDto.getCustomerDto()));
		invoiceFound.setCustomer(cust);
		if(invoiceFound ==null) {
			return null;
		}
	
		Product pro = new Product();
		Detail detailRegis = new Detail();
		List<Detail> detailsAuxRemov = new ArrayList<Detail>();
		List<Detail> detailsAdd = new ArrayList<Detail>();
		for (DetailDto dtlDto : invoiceDto.getDetailDtos()) {
			
			if(dtlDto.getId()==null) {
				detailsAdd.add(detailRegis);
				//pro=this.productServ.saveProductFromFactura(dtlDto.getProductDto());
				detailRegis=mapDetail.toDetail(dtlDto);
				pro=this.producDao.save(mapPoruct.toProduct(dtlDto.getProductDto()));
				System.out.println("id: pro"+pro.getId());		
				detailRegis.setInvoice(invoiceFound);
				detailRegis.setProduct(pro);
				detailRegis=detailDao.save(detailRegis);
					
			}else
			{
				for (Detail dtl : invoiceFound.getDetails()) {
					
					if(dtlDto.getId()==(dtl.getId())) {
						dtl.setAmount(dtlDto.getAmount());
						dtl.setProduct(mapPoruct.toProduct(dtlDto.getProductDto()));
						detailsAuxRemov.add(dtl);
						//System.out.println("entro");
					}else {
					
					}	
			}
				
			}
			
			
		}
//		for (Detail detail : detailsAuxRemov) {
//			for (Detail detail2 : invoiceFound.getDetails()) {
//				if(detail!=detail2) {
//					
//				}
//			}
//		}
		for(int i=0; i<detailsAuxRemov.size(); i++) {
			Detail detal = new Detail();
			System.out.println("canti"+i);
			for(int j=0; j<invoiceFound.getDetails().size();j++) {
				if(detailsAuxRemov.get(i).getId()!=invoiceFound.getDetails().get(j).getId()) {
					detal = detailDao.findById(invoiceFound.getDetails().get(j).getId()).orElse(null);
					System.out.println("canti:"+invoiceFound.getDetails().get(j).getAmount());
	
					invoiceFound.getDetails().remove(detal);
					invoiceDao.save(invoiceFound);
					//detailDao.delete(detal);
					//invoiceFound.getDetails().get(j).setInvoice(null);
					//this.detailDao.delete(invoiceFound.getDetails().get(j));
					System.out.println("elimino"+j);
				}
			}
		}
		
		for (Detail detail : detailsAdd) {			
			invoiceFound.getDetails().add(detail);
			detail.setInvoice(invoiceFound);
			
		}
//		for (Detail detail :invoiceFound.getDetails()) {
//			System.out.println("final: "+detail.getProduct().getName());
//			
//		}
		return mapInvoiceAdd.toInvoiceDto((invoiceFound));

		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * busquedas terminada de aqui ne adelante
	 */
	
	/**
	 * busqueda de factura por nombre patron nombre cliente Finalizado
	 */
	@Override
	public List<InvoiceReadDto> getByCustomer(String nameCustomer) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByCustemer(nameCustomer);
		//System.out.println("invoices: "+ invoices);
		return mapInvoice.toDtos(invoices);
	}

	
	/**
	 * retorna todos las facturas con formato
	 */
	@Override
	public List<InvoiceReadDto> getAll() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = this.invoiceDao.findAll();
		return mapInvoice.toDtos(invoices);
	}

	
	@Override
	public List<InvoiceReadDto> getAllByDate(LocalDate desde,LocalDate hasta) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByBeetwenFecha(desde, hasta);
		return mapInvoice.toDtos(invoices);
	}

	
	@Override
	public InvoiceDto getOne(Long id) {
		Invoice invoice = new Invoice();
		invoice=this.invoiceDao.findById(id).orElse(null);
		if(invoice==null) {
			return null;
		}

		return this.mapInvoiceAdd.toInvoiceDto(invoice);
	}

}



































