package com.prueba.factura.invoice.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.factura.invoice.domain.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	
	@Query(value ="select * from factura a where a.id= ?1", nativeQuery = true)
	List<Invoice> getByCustemer (Long id);
}
