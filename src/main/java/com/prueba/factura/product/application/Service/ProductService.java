package com.prueba.factura.product.application.Service;

import com.prueba.factura.product.domain.entity.Product;
import com.prueba.factura.product.infrestructure.dto.ProductDto;

public interface ProductService {
	public Product saveProductFromFactura(ProductDto productDto) ;
}
