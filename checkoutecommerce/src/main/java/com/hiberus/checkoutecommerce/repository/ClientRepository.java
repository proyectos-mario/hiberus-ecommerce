package com.hiberus.checkoutecommerce.repository;

import com.hiberus.checkoutecommerce.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
