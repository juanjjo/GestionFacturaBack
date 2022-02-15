package com.prueba.factura.customer.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prueba.factura.customer.domain.entity.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = "SELECT * FROM cliente c WHERE c.email = ?1 ",nativeQuery = true)
	Optional <Customer> findByEmail(String email);
	

}
