package com.prueba.factura.invoice.application.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class InvoiceServiceImpl implements InvoiceService {

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
	private MapProduct mapPoruct;
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
		} else {
			customer = mapCustomer.toCustomer(invoiceDto.getCustomerDto());
			invoice.setCustomer(customer);
		}

		invoice.setDetails(details);

		for (Detail detail : details) {
			detail.setInvoice(invoice);
			for (DetailDto detailDto : invoiceDto.getDetailDtos()) {
				pro = producDao.save(mapPoruct.toProduct(detailDto.getProductDto()));
				detail.setProduct(pro);
			}
		}
		invoice = this.invoiceDao.save(invoice);

		return null;
	}

	@Override
	public InvoiceDto deleteOne(Long id) {
		Invoice invoice = new Invoice();

		Customer customer = new Customer();
		invoice = this.invoiceDao.findById(id).orElse(null);
		if (invoice == null) {
			return null;
		}
		customer = customerRep.findByEmail(invoice.getCustomer().geteMailCustomer()).orElse(null);
		if (customer != null) {
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
		Detail detal = new Detail();
		boolean ban = false;
		if (invoiceFound == null) {
			return null;
		}

		Product pro = new Product();
		Detail detailRegis = new Detail();
		List<Detail> detailsAuxRemov = new ArrayList<Detail>();
		List<Detail> detailsAdd = new ArrayList<Detail>();
		for (DetailDto dtlDto : invoiceDto.getDetailDtos()) {

			if (dtlDto.getId() == null) {
				detailsAdd.add(detailRegis);
				detailRegis = mapDetail.toDetail(dtlDto);
				pro = this.productServ.saveProductFromFactura(dtlDto.getProductDto());
				detailRegis.setInvoice(invoiceFound);
				detailRegis.setProduct(pro);
				detailRegis = detailDao.save(detailRegis);

			} else {
				for (Detail dtl : invoiceFound.getDetails()) {

					if (dtlDto.getId() == (dtl.getId())) {
						dtl.setAmount(dtlDto.getAmount());
						dtl.setProduct(mapPoruct.toProduct(dtlDto.getProductDto()));
						detailsAuxRemov.add(dtl);
					} else {

					}
				}

			}

		}
		deleteDetailRestantes(detailsAuxRemov, invoiceFound.getDetails(), invoiceFound);
		
		return mapInvoiceAdd.toInvoiceDto((invoiceFound));

	}
	
	public void deleteDetailRestantes(List<Detail>detailIgual,List<Detail>detailles,Invoice invoiceFound) {
		boolean ban = false;
		Detail detail = new Detail();
		for (int i = 0; i < detailles.size(); i++) {
			for (int j = 0; j < detailIgual.size(); j++) {
				if (detailIgual.get(j).getId() == invoiceFound.getDetails().get(i).getId()) {
					ban = true;
				}
			}
			if (ban == false) {
				detail = detailDao.findById(invoiceFound.getDetails().get(i).getId()).orElse(null);
				invoiceFound.getDetails().remove(detail);
				invoiceDao.save(invoiceFound);
			}
			ban = false;
		}
		
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
	public List<InvoiceReadDto> getAllByDate(LocalDate desde, LocalDate hasta) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoiceDao.getByBeetwenFecha(desde, hasta);
		return mapInvoice.toDtos(invoices);
	}

	@Override
	public InvoiceDto getOne(Long id) {
		Invoice invoice = new Invoice();
		invoice = this.invoiceDao.findById(id).orElse(null);
		if (invoice == null) {
			return null;
		}

		return this.mapInvoiceAdd.toInvoiceDto(invoice);
	}

}
