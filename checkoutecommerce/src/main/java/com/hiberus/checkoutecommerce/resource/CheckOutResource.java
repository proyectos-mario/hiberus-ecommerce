package com.hiberus.checkoutecommerce.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import voecommercecom.hiberus.commons.vo.OrderVO;
import voecommercecom.hiberus.commons.vo.OutBillVO;
import voecommercecom.hiberus.commons.vo.OutCheckOutVO;
import voecommercecom.hiberus.commons.vo.OutLogisticVO;

/**
 * Service of CheckOut
 * 
 * @author mario
 *
 */
@RestController
@RequestMapping("/api/")
@Api(tags = "CheckOutResource")
public class CheckOutResource {
	
	@Autowired 
	private RestTemplate restTemplate;
	
	@PostMapping (value = "/checkout")
	@ApiOperation(value = "Inicializar CheckOut", notes = "Initialiser service of CheckOut")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutCheckOutVO> checkOutProcess(@RequestBody OrderVO orderVO) {
		
		
		/*
		 * In this part
		 * The CheckOut service call
		 * Bill service and get and answer
		 * */
		
		OutBillVO billResponse=restTemplate.postForObject(
				"http://localhost:7000/api/bill",
				orderVO,
				OutBillVO.class);
		
		/*
		 * In this part
		 * The CheckOut service call
		 * Logistic service and get and answer
		 * */
		
		OutLogisticVO logisticResponse=restTemplate.postForObject(
				"http://localhost:7001/api/logistic",
				orderVO,
				OutLogisticVO.class);
		
		System.out.println("Bill response:"+billResponse.getMensaje());
		System.out.println("Logistic response:"+logisticResponse.getMensaje());
		
		
		/**/
		OutCheckOutVO test=new OutCheckOutVO();
		test.setMensaje("mensaje");
		test.setBillOut(billResponse);
		test.setLogisticOut(logisticResponse);
		test.setProcesoOk(true);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
}
