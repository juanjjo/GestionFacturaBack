package com.prueba.factura.product.infrestructure.mappers;

import org.mapstruct.Mapper;

import com.prueba.factura.product.domain.entity.Product;
import com.prueba.factura.product.infrestructure.dto.ProductDto;

@Mapper(
		componentModel = "spring"
)
public interface MapProduct {
	
	ProductDto toProducDto (Product product);
	
	Product toProduct (Product product);
}
