package com.hiberus.billecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.billecommerce.entities.Product;
import com.hiberus.billecommerce.repository.ProductRepository;

@Service
@Transactional(readOnly = true)
public class BillService {
	@Autowired
	private ProductRepository ProductRepository;

	public Boolean existProduct(Integer ProductId) {
		Optional<Product> cl=ProductRepository.findById(ProductId);
		return cl.isPresent();
	}

	public List<Product> getProducts() {
		return ProductRepository.findAll();
	}
	
	

}
