package voecommercecom.hiberus.commons.vo;

import java.io.Serializable;

public class OutBillVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean processOk;
	
	private String message;
	
	private Double sum;

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Boolean getProcessOk() {
		return processOk;
	}

	public void setProcessOk(Boolean processOk) {
		this.processOk = processOk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
