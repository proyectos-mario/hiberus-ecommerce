package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class GenerateOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckOutVO checkOut;
	private Double sum;
	public CheckOutVO getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(CheckOutVO checkOut) {
		this.checkOut = checkOut;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
}
