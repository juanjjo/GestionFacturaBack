package com.prueba.factura.product.application.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.factura.customer.domain.entity.Customer;
import com.prueba.factura.product.application.Service.ProductService;
import com.prueba.factura.product.domain.entity.Product;
import com.prueba.factura.product.domain.repository.ProductRepository;
import com.prueba.factura.product.infrestructure.dto.ProductDto;
import com.prueba.factura.product.infrestructure.mappers.MapProduct;


@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productDao;
	@Autowired
	private MapProduct mapProduct;
	
	
	@Override
	public Product saveProductFromFactura(ProductDto productDto) {
		Product productFound = new Product();
		Product productSave = new Product();
		if(productDto==null) {
			return null;
		}
		productSave= mapProduct.toProduct(productDto);
		
		if (productDto.getId() != null) {
			productFound = this.productDao.findById(productDto.getId()).orElse(null);
			if(productFound!=null) {			
				productSave.setId(productFound.getId());
				productSave=this.productDao.save(productSave);
			}else {
				return null;
			}
			
		}else {
			productSave=this.productDao.save(productSave);
		}
		return productSave;
		
	}
}
