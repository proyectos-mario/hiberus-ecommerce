package com.hiberus.logisticecommerce.resource;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hiberus.logisticecommerce.entities.Order;
import com.hiberus.logisticecommerce.service.LogisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import voecommercecom.hiberus.commons.vo.GenerateOrderVO;
import voecommercecom.hiberus.commons.vo.OutLogisticVO;

/**
 * Service of logistic
 * 
 * @author mario
 *
 */
@RestController
@RequestMapping("/api/")
@Api(tags = "LogisticResource")
public class LogisticEcommerceResource {
	
	@Autowired
	private LogisticService logisticService;
	
	/**
	 * With this service you can get all clients for testing
	 * @return
	 */
	@GetMapping(value = "/getOrders")
	@ApiOperation(value = "List Orders", notes = "List orders service,  With this service you can get all Orders for testing")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Orders found"),
			@ApiResponse(code = 404, message = "Orders list not found") })
	public ResponseEntity<List<Order>> getClients() {
		List<Order> orders=this.logisticService.getOrders();
		return ResponseEntity.ok(orders);

	}
	
	
	@PostMapping (value = "/logistic")
	@ApiOperation(value = "Initialize logistic", notes = "Initialiser service of logistic, you receive GenerateOrder object and get response of execution of the process, and save the order history and you can consult it with getOrders service")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutLogisticVO> logisticProcess(@RequestBody GenerateOrderVO checkOutVO) {
		OutLogisticVO logisticOut=new OutLogisticVO();
		try {
		
		Integer numberOrder=logisticService.getNumberOrder();
		
		logisticService.saveOrder(checkOutVO,numberOrder);
		
		logisticOut.setMensaje("Logistic out ok");
		logisticOut.setNumberOrder(numberOrder);
		logisticOut.setProcesoOk(true);
		
		return new ResponseEntity<>(logisticOut, HttpStatus.OK);
		} catch (Exception e) {
			logisticOut.setMensaje("Logistic process fail:" + e.getMessage());
			logisticOut.setProcesoOk(false);
			LogManager.getLogger(this.getClass().getName()).error("Logistic process fail:"+ e.getMessage());
			return new ResponseEntity<>(logisticOut, HttpStatus.BAD_REQUEST);
		}
	}
	
}
