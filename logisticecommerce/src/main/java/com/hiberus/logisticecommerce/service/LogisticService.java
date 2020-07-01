package com.hiberus.logisticecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.logisticecommerce.repository.LogisticDao;

@Service
@Transactional(readOnly = true)
public class LogisticService {
	@Autowired
	private LogisticDao logisticDao;
	
	@Transactional
	public Integer getNumberOrder() {
		return logisticDao.getNumberOrder();
	}
	
	

}
