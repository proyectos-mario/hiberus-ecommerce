package com.hiberus.logisticecommerce.resource;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.logisticecommerce.service.LogisticService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import voecommercecom.hiberus.commons.vo.OrderVO;
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
	
	@PostMapping (value = "/logistic")
	@ApiOperation(value = "Inicializar logistic", notes = "Initialiser service of logistic")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutLogisticVO> logisticProcess(@RequestBody OrderVO orderVO) {
		OutLogisticVO logisticOut=new OutLogisticVO();
		try {
		
		Integer numberOrder=logisticService.getNumberOrder();
		
		
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
