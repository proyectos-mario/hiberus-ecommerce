package com.hiberus.checkoutecommerce.resource;

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
import org.springframework.web.client.RestTemplate;

import com.hiberus.checkoutecommerce.entities.Client;
import com.hiberus.checkoutecommerce.enums.APIEnum;
import com.hiberus.checkoutecommerce.service.CheckOutService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import voecommercecom.hiberus.commons.vo.CheckOutVO;
import voecommercecom.hiberus.commons.vo.GenerateOrderVO;
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
	
	@Autowired
	private CheckOutService checkOutService;

	
	
	private boolean existClient(Integer clientId) {
		return checkOutService.existClient(clientId);
	}
	
	
	/**
	 * With this service you can get all clients for testing
	 * @return
	 */
	@GetMapping(value = "/getClients")
	@ApiOperation(value = "List Clients", notes = "List clients service,  With this service you can get all clients for testing.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Clients found"),
			@ApiResponse(code = 404, message = "Clients list not found") })
	public ResponseEntity<List<Client>> getClients() {
		List<Client> clients=this.checkOutService.getClients();
		return ResponseEntity.ok(clients);

	}
	
	@PostMapping(value = "/checkout")
	@ApiOperation(value = "Initialize CheckOut", notes = "Initialiser service of CheckOut,With this service you can run checkout process, you receive checkout object and get response of execution of the process")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutCheckOutVO> checkOutProcess(@RequestBody CheckOutVO checkout) {
		OutCheckOutVO checkOut = new OutCheckOutVO();
		
		
		
		try {
			
			if(checkout==null||checkout.getClientId()==null||!existClient(checkout.getClientId())) {
				String message="Client doesn´t exist with Id="+(checkout!=null?""+checkout.getClientId():null);
				LogManager.getLogger(this.getClass().getName()).error(message);
				throw new Exception(message);
			}

///////////////////---------------------------------------------------------------
			/*
			 * In this part The CheckOut service call Bill service and get a response
			 */

			OutBillVO billResponse = restTemplate.postForObject(APIEnum.BILL_API.getUrlApi(), checkout, OutBillVO.class);

///////////////////---------------------------------------------------------------

			/*
			 * In this part The CheckOut service call Logistic service and get a response
			 */
			GenerateOrderVO go= new GenerateOrderVO();
			go.setCheckOut(checkout);
			go.setSum(billResponse.getSum());
			OutLogisticVO logisticResponse = restTemplate.postForObject(APIEnum.LOGISTIC_API.getUrlApi(), go,
					OutLogisticVO.class);

///////////////////// ---------------------------------------------------------------

			LogManager.getLogger(this.getClass().getName()).info("Bill response:" + billResponse.getMensaje());
			LogManager.getLogger(this.getClass().getName()).info("Logistic response:" + logisticResponse.getMensaje());

			/* CheckOut Ok */

			checkOut.setMensaje("Correct CheckOut");
			checkOut.setBillOut(billResponse);
			checkOut.setLogisticOut(logisticResponse);
			checkOut.setProcesoOk(true);

			return new ResponseEntity<>(checkOut, HttpStatus.OK);
		} catch (Exception e) {
			checkOut.setMensaje("CheckOut process fail:" + e.getMessage());
			checkOut.setProcesoOk(false);
			LogManager.getLogger(this.getClass().getName()).error("Check out process fail:"+ e.getMessage());
			return new ResponseEntity<>(checkOut, HttpStatus.BAD_REQUEST);
		}
	}

}
