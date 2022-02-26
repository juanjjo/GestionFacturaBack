package com.prueba.factura.invoice.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.factura.invoice.domain.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	
	@Query(value ="select a.id, a.descripcion, a.fecha, a.folio, a.observacion ,a.cliente_id"
			+ " from factura a where a.descripcion like %?1%", nativeQuery = true)
	Invoice getByCustemerByName (String name);
	
	@Query(value ="select a.id, a.descripcion, a.fecha, a.folio, a.observacion, a.cliente_id"
			+ " from factura a inner join cliente c on a.cliente_id = c.id where c.nombre like %?1%", nativeQuery = true)
	List<Invoice> getByCustemer (String name);
}
