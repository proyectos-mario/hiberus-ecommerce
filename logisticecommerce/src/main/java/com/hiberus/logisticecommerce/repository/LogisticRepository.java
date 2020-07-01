package com.hiberus.logisticecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hiberus.logisticecommerce.entities.Order;



public interface LogisticRepository extends JpaRepository<Order, Integer> {
	
	
	
	
	@Query(value="SELECT nextval('ecommercelogistic.order_id_seq')",nativeQuery = true)
	public Integer getNumberOrder();

   
}
