package com.prueba.factura.invoice.infrestructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prueba.factura.customer.infrestructure.mappers.MapCustomer;
import com.prueba.factura.invoice.domain.entity.Detail;
import com.prueba.factura.invoice.infrestructure.dto.DetailDto;
import com.prueba.factura.product.infrestructure.mappers.MapProduct;


@Mapper(
		componentModel = "spring",
		uses = {MapProduct.class}
)

public interface MapDetail {
	
	
	
	DetailDto toDetailDto(DetailDto detailDto);
	Detail toDetail(Detail detail);
	
	List<Detail> toDetails(List<DetailDto> detailsDto);
}
