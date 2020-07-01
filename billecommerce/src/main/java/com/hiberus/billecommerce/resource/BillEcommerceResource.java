package com.hiberus.billecommerce.resource;

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

import com.hiberus.billecommerce.entities.Product;
import com.hiberus.billecommerce.service.BillService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import voecommercecom.hiberus.commons.vo.CheckOutVO;
import voecommercecom.hiberus.commons.vo.OutBillVO;
import voecommercecom.hiberus.commons.vo.ProductVO;

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
	
	
	@Autowired
	private BillService billService;

	
	/**
	 * With this service you can get all products for testing
	 * @return
	 */
	@GetMapping(value = "/getProducts")
	@ApiOperation(value = "List Products", notes = "List products service,  With this service you can get all products for testing")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Products found"),
			@ApiResponse(code = 404, message = "Products list not found") })
	public ResponseEntity<List<Product>> getClients() {
		List<Product> products=this.billService.getProducts();
		return ResponseEntity.ok(products);

	}
	
	
	private boolean existProduct(Integer productId) {
		return billService.existProduct(productId);
	}

	/**
	 * Service that receive order and
	 * return the sum of values
	 * @param orderVO
	 * @return
	 */
	@PostMapping(value = "/bill")
	@ApiOperation(value = "Initialize bill", notes = "Initialiser service of bill, It receive CheckOut service with products and get response of service with the sum of products values")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Proccess executed correctly"),
			@ApiResponse(code = 400, message = "Error in the process") })
	public ResponseEntity<OutBillVO> billProcess(@RequestBody CheckOutVO orderVO) {
		OutBillVO billResponse = new OutBillVO();
		try {
			
			

			if (orderVO != null) {

				Double sum = 0D;
				for (ProductVO product : orderVO.getProducts()) {
					
					if(product==null||product.getId()==null||!existProduct(product.getId())) {
						String message="Product doesn´t exist with Id="+(product!=null?""+product.getId():null);
						LogManager.getLogger(this.getClass().getName()).error(message);
						throw new Exception(message);
					}
					
					if(product.getCost() == null ||product.getQuantity()==null) {
						throw new Exception("Null Cost in productId="+product.getId()+" or Quantity value--->cost:"+product.getCost()+", Quantity--->"+ product.getQuantity());
					}
					sum += product.getCost()*product.getQuantity();
				}
				billResponse.setSum(sum);
				billResponse.setMensaje("Bill process pass ok");
				billResponse.setProcesoOk(true);
				LogManager.getLogger(this.getClass().getName()).info("Bill sum process Ok----> Sum=" + sum);
			} else {
				throw new Exception("Null order value");

			}
			return new ResponseEntity<>(billResponse, HttpStatus.OK);
		} catch (Exception e) {
			billResponse.setMensaje("Bill process fail:"+e.getMessage());
			billResponse.setProcesoOk(false);
			LogManager.getLogger(this.getClass().getName()).error("Bill sum process error");
			return new ResponseEntity<>(billResponse, HttpStatus.BAD_REQUEST);
		}

	}

}
