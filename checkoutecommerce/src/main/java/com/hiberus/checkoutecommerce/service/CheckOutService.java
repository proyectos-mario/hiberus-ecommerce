package com.hiberus.checkoutecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.checkoutecommerce.entities.Client;
import com.hiberus.checkoutecommerce.repository.ClientRepository;

@Service
@Transactional(readOnly = true)
public class CheckOutService {
	@Autowired
	private ClientRepository clientRepository;

	public Boolean existClient(Integer clientId) {
		Optional<Client> cl=clientRepository.findById(clientId);
		return cl.isPresent();
	}

	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	

}
