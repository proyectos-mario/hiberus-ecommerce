package com.hiberus.logisticecommerce.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping (value = "/logistic")
	@ApiOperation(value = "Inicializar logistic", notes = "Initialiser service of logistic")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutLogisticVO> logisticProcess(@RequestBody OrderVO orderVO) {
		
		OutLogisticVO test=new OutLogisticVO();
		test.setMensaje("Logistic out ok");
		test.setProcesoOk(true);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
}
