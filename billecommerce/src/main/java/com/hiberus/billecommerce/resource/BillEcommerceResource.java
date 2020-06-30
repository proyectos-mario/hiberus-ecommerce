package com.hiberus.billecommerce.resource;

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
import voecommercecom.hiberus.commons.vo.OutBillVO;

/**
 * Service of bill
 * 
 * @author mario
 *
 */
@RestController
@RequestMapping("/api/")
@Api(tags = "BillResource")
public class BillEcommerceResource {
	
	@PostMapping (value = "/bill")
	@ApiOperation(value = "Inicializar bill", notes = "Initialiser service of bill")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutBillVO> billProcess(@RequestBody OrderVO orderVO) {
		
		OutBillVO test=new OutBillVO();
		test.setMensaje("Bill pass ok");
		test.setProcesoOk(true);
		
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
	
}
