package com.prueba.factura.invoice.domain.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.factura.invoice.domain.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	
	@Query(value ="select a.id, a.descripcion, a.fecha, a.folio, a.observacion ,a.cliente_id"
			+ " from factura a where a.descripcion like %?1%", nativeQuery = true)
	public Invoice getByCustemerByName (String name);
	
	@Query(value ="select a.id, a.descripcion, a.fecha, a.folio, a.observacion, a.cliente_id"
			+ " from factura a inner join cliente c on a.cliente_id = c.id where c.nombre like %?1%", nativeQuery = true)
	public List<Invoice> getByCustemer (String name);
	

	@Query(value= "SELECT * FROM factura WHERE fecha >=?1 AND fecha <= ?2", nativeQuery = true)
	public List<Invoice> getByBeetwenFecha(@Param("from") LocalDate from, @Param("until") LocalDate until);
}










