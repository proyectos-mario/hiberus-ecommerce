package com.hiberus.billecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.billecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
