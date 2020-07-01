package com.hiberus.logisticecommerce.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.logisticecommerce.entities.Order;
import com.hiberus.logisticecommerce.repository.LogisticRepository;

import voecommercecom.hiberus.commons.vo.GenerateOrderVO;

@Service
@Transactional(readOnly = true)
public class LogisticService {
	@Autowired
	private LogisticRepository logisticRepository;
	
	@Transactional
	public Integer getNumberOrder() {
		return logisticRepository.getNumberOrder();
	}
	@Transactional
	public void saveOrder(GenerateOrderVO generateOrder, Integer numberOrder) throws Exception {
		try {
		Order or=new Order();
		or.setAddress(generateOrder.getCheckOut().getDirection());
		or.setDateOrder(generateOrder.getCheckOut().getDate());
		or.setProductTotal(generateOrder.getSum());
		or.setOrderId(numberOrder);
		or.setClientId(generateOrder.getCheckOut().getClientId());
		or.setDateGeneration(new Date());
		logisticRepository.save(or);
		}
		catch(Exception e){
			e.printStackTrace();
			String message="Error save order:"+ e.getMessage();
			LogManager.getLogger(this.getClass().getName()).error(message);
			throw new Exception(message);
		}
	}
	public List<Order> getOrders() {
		return logisticRepository.findAll();
	}
	
	

}
