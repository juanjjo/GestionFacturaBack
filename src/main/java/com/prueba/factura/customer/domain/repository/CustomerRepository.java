package com.prueba.factura.customer.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba.factura.customer.domain.entity.Customer;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	
	

}
