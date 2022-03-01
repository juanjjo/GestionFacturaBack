package com.prueba.factura.invoice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.factura.invoice.domain.entity.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long>{
	
}
